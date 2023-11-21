package com.example.inhindi

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi

class animal : AppCompatActivity() {
    private var mediaPlayer:MediaPlayer?=null
    private lateinit var audioManager:AudioManager
    private var changeListener=AudioManager.OnAudioFocusChangeListener{ focusChange->
        when(focusChange){
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT,
            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK-> {
                mediaPlayer?.pause()
                mediaPlayer?.seekTo(0)
            }
            AudioManager.AUDIOFOCUS_GAIN-> mediaPlayer?.start()
            AudioManager.AUDIOFOCUS_LOSS-> releaseMediaPlayer()
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.words_list)
        audioManager= getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val words= arrayOf(
            word("Dog", "कुत्ता (Kutta)", R.mipmap.lily, R.raw.dog),
            word("Cat", "बिल्ली (Billi)", R.mipmap.sunflower, R.raw.cat),
            word("Elephant" ,"हाथी (Haathi)", R.mipmap.tulip, R.raw.elephant),
            word("Lion" ,"शेर (Sher)", R.mipmap.jasmine, R.raw.lion),
            word("Tiger", "बाघ (Bagh)", R.mipmap.newrosered, R.raw.tiger),
            word("Cow",  "गाय (Gai)", R.mipmap.rose, R.raw.cow),
            word("Horse","घोड़ा (Ghoda)", R.mipmap.hibiscus, R.raw.horse),
            word("Rabbit","खरगोश (Khargosh)", R.mipmap.daisy, R.raw.rabbit),
            word("Monkey", "मंकी (Manki)", R.mipmap.newrosered, R.raw.monkey ),
            word("Bird", "पक्षी (Pakshi)", R.mipmap.newrose, R.raw.bird),
            word("Fish" ,"मछली (Machhli)", R.mipmap.daisy, R.raw.fish),
            word("Snake","सांप (Saap)", R.mipmap.tulip, R.raw.snake),
            word("Bear" ,"भालू (Bhalu)", R.mipmap.daffodil, R.raw.bear),
            word("Deer","हिरण (Hiran)", R.mipmap.lily, R.raw.deer),
            word("Goat","बकरी (Bakri)", R.mipmap.daffodil, R.raw.goat))
        val animalLayout=findViewById<ListView>(R.id.item_List)
        val itemAdapter=customisedWordAdapter(this, words, R.color.dark_green)
        animalLayout.adapter=itemAdapter
        animalLayout.setOnItemClickListener { _, _, position, _ ->
            val currentWord=words[position]

            releaseMediaPlayer()
            val request:Int=audioManager.requestAudioFocus( changeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
            if(request==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                mediaPlayer=MediaPlayer.create(this, currentWord.getAudioResourceId())
                mediaPlayer?.start()
                mediaPlayer?.setOnCompletionListener {
                    releaseMediaPlayer()
                }
            }
        }
    }
    override fun onStop() {
        super.onStop()
        releaseMediaPlayer()
    }
    private fun releaseMediaPlayer(){
        if(mediaPlayer!=null){
            mediaPlayer!!.release()
            mediaPlayer=null
            audioManager.abandonAudioFocus(changeListener)
        }
    }
}

package com.example.inhindi

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class fruit : AppCompatActivity() {
    private var mediaPlayer:MediaPlayer?=null
    private lateinit var audioManager: AudioManager
    private var changeListener= AudioManager.OnAudioFocusChangeListener{ focusChange->
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.words_list)
        audioManager= getSystemService(Context.AUDIO_SERVICE) as AudioManager

        val words=ArrayList<word>()
        words.add(word("Apple", "सेब (Seb)", R.mipmap.daffodil, R.raw.seb))
        words.add(word("Banana", "केला (Kela)", R.mipmap.rose, R.raw.kela))
        words.add(word("Orange", "संतरा (Santra)", R.mipmap.newrose, R.raw.santra))
        words.add(word("Mango", "आम (Aam)", R.mipmap.marigold, R.raw.aam))
        words.add(word("Grapes", "अंगूर (Angoor)", R.mipmap.sunflower, R.raw.angoor))
        words.add(word("Pineapple", "अनानास (Ananas)", R.mipmap.tulip, R.raw.ananas))
        words.add(word("Strawberry", "स्ट्रॉबेरी (Strawberry)", R.mipmap.lily, R.raw.strawberry))
        words.add(word("Watermelon", "तरबूज (Tarbooj)", R.mipmap.daisy, R.raw.tarbooj))
        words.add(word("Kiwi", "कीवी (Kiwi)", R.mipmap.jasmine, R.raw.kiwi))
        words.add(word("Pomegranate", "अनार (Anar)", R.mipmap.hibiscus, R.raw.anar))
        words.add(word("Papaya", "पपीता (Papita)", R.mipmap.newrosered, R.raw.papita))
        words.add(word("Guava", "अमरूद (Amrood)", R.mipmap.jasmine, R.raw.amrood))

        val itemList=words.toTypedArray()    //it convert the arrayList into array
        val newAdapter=customisedWordAdapter(this, itemList, R.color.dark_pink)
        val listView=findViewById<ListView>(R.id.item_List)
        listView.adapter=newAdapter
        listView.setOnItemClickListener { _, _, position, _ ->
            val wordPosition=words[position]
            releaseMediaPlayer()
            val request:Int=audioManager.requestAudioFocus( changeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
            if(request==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mediaPlayer = MediaPlayer.create(this, wordPosition.getAudioResourceId())
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
        }
    }
}
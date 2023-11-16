package com.example.inhindi

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class city : AppCompatActivity() {
    private var mediaPlayer:MediaPlayer?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.words_list)

        val words=ArrayList<word>()
        words.add(word("New York", "न्यू यॉर्क", R.raw.cow))
        words.add(word("London", "लंदन", R.raw.dog))
        words.add(word("Tokyo", "टोक्यो", R.raw.deer))
        words.add(word("Paris", "पेरिस", R.raw.goat))
        words.add(word("Sydney", "सिडनी", R.raw.deer))
        words.add(word("Beijing", "बीजिंग", R.raw.horse))
        words.add(word("Mumbai", "मुंबई", R.raw.lily))
        words.add(word("Rome", "रोम", R.raw.lion))
        words.add(word("Dubai", "दुबई", R.raw.dog))
        words.add(word("Berlin", "बर्लिन", R.raw.kela))
        words.add(word("Rio de Janeiro", "रियो डी जनेरो", R.raw.monkey))
        words.add(word("Cairo", "काहिरा", R.raw.cat))
        words.add(word("Toronto", "टोरॉंटो", R.raw.anar))
        words.add(word("Seoul", "सोल", R.raw.bird))
        words.add(word("Cape Town", "केप टाउन", R.raw.fish))
        words.add(word("Moscow", "मॉस्को", R.raw.hibiscus))
        words.add(word("Singapore", "सिंगापुर", R.raw.kela))
        words.add(word("Bangkok", "बैंकॉक", R.raw.kiwi))
        words.add(word("Los Angeles", "लॉस एंजिल्स", R.raw.anar))
        words.add(word("Delhi", "दिल्ली", R.raw.ananas))
        val itemList=words.toTypedArray()    //it convert the arrayList into array
        val newAdapter=customisedWordAdapter(this, itemList, R.color.dark_brown)
        val listView=findViewById<ListView>(R.id.item_List)
        listView.adapter=newAdapter
        listView.setOnItemClickListener { _, _, position, _ ->
            val currWord=words[position]
            releaseMediaPlayer()
            mediaPlayer=MediaPlayer.create(this, currWord.getAudioResourceId())
            mediaPlayer?.start()
            mediaPlayer?.setOnCompletionListener {
                   releaseMediaPlayer()
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
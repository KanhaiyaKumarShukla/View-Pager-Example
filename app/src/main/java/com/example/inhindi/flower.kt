package com.example.inhindi

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class flower : AppCompatActivity() {
    private var mediaPlayer:MediaPlayer? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.words_list)

        val words=ArrayList<word>()
        words.add(word("Rose", "गुलाब ()", R.mipmap.newrosered, R.raw.rose))
        words.add(word("Lily", "कन्दमूल (Kandamool)",R.mipmap.lily, R.raw.lily))
        words.add(word("Sunflower", "सूरजमुखी (Surajmukhi)",R.mipmap.sunflower, R.raw.sunflower))
        words.add(word("Tulip", "ट्यूलिप (Tulip)",R.mipmap.tulip, R.raw.tulip))
        words.add(word("Daffodil", "डैफोडिल (Daffodil)",R.mipmap.daffodil, R.raw.daffodil))
        words.add(word("Jasmine", "चमेली (Chameli)",R.mipmap.jasmine, R.raw.jasmine))
        words.add(word("Marigold", "गेंदा (Genda)",R.mipmap.marigold, R.raw.shantika))
        words.add(word("Daisy", "डेज़ी (Daisy)",R.mipmap.daisy, R.raw.daisy))
        words.add(word("Orchid", "ऑर्किड (Orchid)",R.mipmap.daffodil, R.raw.orchid))
        words.add(word("Carnation", "कार्नेशन (Carnation)",R.mipmap.tulip, R.raw.carnation))
        words.add(word("Hibiscus", "गुड़हल (Gudhal)",R.mipmap.daisy, R.raw.hibiscus))
        words.add(word("Chrysanthemum", "शांतिका (Shantika)" ,R.mipmap.lily, R.raw.chrysanthemum))

        val itemList=words.toTypedArray()    //it convert the arrayList into array
        val newAdapter=customisedWordAdapter(this, itemList, R.color.dark_blue)
        val listView=findViewById<ListView>(R.id.item_List)
        listView.adapter=newAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val viewPosition= words[position]
            releaseMediaPlayer()
            mediaPlayer=MediaPlayer.create(this, viewPosition.getAudioResourceId())
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
package com.example.inhindi

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.core.content.getSystemService

class FlowerFragment : Fragment() {

    private var mediaPlayer: MediaPlayer? =null
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView:View?=inflater.inflate(R.layout.words_list, container, false)
        audioManager= activity?.getSystemService(Context.AUDIO_SERVICE) as AudioManager

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
        val newAdapter=customisedWordAdapter(requireActivity(), itemList, R.color.dark_blue)
        val listView=rootView?.findViewById<ListView>(R.id.item_List)
        listView?.adapter=newAdapter

        listView?.setOnItemClickListener { _, _, position, _ ->
            val viewPosition= words[position]
            releaseMediaPlayer()
            val request:Int=audioManager.requestAudioFocus( changeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
            if(request==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mediaPlayer = MediaPlayer.create(activity, viewPosition.getAudioResourceId())
                mediaPlayer?.start()
                mediaPlayer?.setOnCompletionListener {
                    releaseMediaPlayer()
                }
            }
        }
        return rootView
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
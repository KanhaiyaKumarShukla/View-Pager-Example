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

class FruitFragment : Fragment() {
    private var mediaPlayer: MediaPlayer?=null
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
        val newAdapter=customisedWordAdapter(requireActivity(), itemList, R.color.dark_pink)
        val listView=rootView?.findViewById<ListView>(R.id.item_List)
        listView?.adapter=newAdapter
        listView?.setOnItemClickListener { _, _, position, _ ->
            val wordPosition=words[position]
            releaseMediaPlayer()
            val request:Int=audioManager.requestAudioFocus( changeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
            if(request==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mediaPlayer = MediaPlayer.create(activity, wordPosition.getAudioResourceId())
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
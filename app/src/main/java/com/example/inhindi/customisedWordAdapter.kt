package com.example.inhindi

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class customisedWordAdapter(context: Activity, words:Array<word>, private val colorResourceId:Int) : ArrayAdapter<word>(context,0,words){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView=convertView
        if(listItemView == null){
            listItemView= LayoutInflater.from(context).inflate(
                R.layout.item_layout, parent,false
            )
        }

        val currentWord=getItem(position)
        val tvEnglishTrans=listItemView!!.findViewById<TextView>(R.id.tv1)
        tvEnglishTrans.text=currentWord?.getEnglishTrans()

        val tvHindiTrans= listItemView.findViewById<TextView>(R.id.tv2)
        tvHindiTrans.text=currentWord?.getHindiTrans()

        val imageView= listItemView.findViewById<ImageView>(R.id.image)

        //to fit the image view we have following methods:
        //method - 1:
        currentWord?.getImageResourceId()?.let {
            if(it!=-1){
                imageView.setImageResource(it)
                imageView.visibility = View.VISIBLE
            }
            else imageView.visibility = View.GONE
        }

//        method-2:
//        if (currentWord?.getImageResourceId() != word.NO_IMAGE_PROVIDED) {
//            currentWord?.getImageResourceId()?.let { imageView.setImageResource(it) }
//            imageView.visibility = View.VISIBLE
//        } else {
//            imageView.visibility = View.GONE
//        }

//        method-3:
//        if (currentWord?.hasImage() == true) {
//            imageView.setImageResource(currentWord.getImageResourceId())
//            imageView.visibility = View.VISIBLE
//        } else {
//            imageView.visibility = View.GONE
//        }

        val textContainer=listItemView.findViewById<View>(R.id.text_container)
        val color:Int=ContextCompat.getColor(context, colorResourceId)  //here context = getContext()
        textContainer.setBackgroundColor(color)

        return listItemView
    }

}
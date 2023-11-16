package com.example.inhindi

class word(private val mEnglishTrans: String,
           private val mHindiTrans: String,
           private val imageResourceId:Int,
           private val audioResourceId:Int) {

    // if you are using method - 2 or 3 for imageView you should use the following object:
    /*companion object {
        const val NO_IMAGE_PROVIDED = -1
    }*/

    constructor(
        mEnglishTrans: String,
        mHindiTrans: String,
        hAudioResourceId:Int) : this( mEnglishTrans,mHindiTrans, -1, hAudioResourceId)    // for method-2 or 3 use NO_IMAGE_PROVIDED
                                                                             //in place of -1 as imageResourceId parameter
    fun getEnglishTrans():String{
        return mEnglishTrans
    }
    fun getHindiTrans():String{
        return mHindiTrans
    }
    fun getImageResourceId():Int{
        return imageResourceId
    }
    // for method -3 you should use this function method to check whether the imageResourceId is present or not.
    /*fun hasImage():Boolean{
        return imageResourceId!= NO_IMAGE_PROVIDED
    }*/
    fun getAudioResourceId():Int{
        return audioResourceId
    }
}
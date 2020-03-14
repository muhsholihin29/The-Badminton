package com.sstudio.thebadminton.menu3.mvp.upload

import android.graphics.Bitmap
import com.sstudio.thebadminton.model.Video

interface UploadView  {

    fun onProgressUpdate(percenage: Int)
    fun showDialog()
    fun dismissDialog()
    fun toast(text: String)
    fun showVideoList(videos: List<Video>)
    fun showThumbnail(bitmap: Bitmap)
}
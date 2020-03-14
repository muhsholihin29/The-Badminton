package com.sstudio.thebadminton.menu3.mvp.upload

import android.net.Uri

interface UploadPresenter {
    fun insertData(key: String, name: String, title: String, overview: String, selectedFileUri: Uri?)
    fun getData()
    fun deleteVideo(id: String, videoUrl: String)
}
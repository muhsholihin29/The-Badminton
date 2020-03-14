package com.sstudio.thebadminton.utils

import com.sstudio.thebadminton.menu3.mvp.upload.UploadView

class ProgressUpdater(private var uploaded: Int, private var fileLength: Long, private var listener: UploadView) : Runnable {
    override fun run() {
        listener.onProgressUpdate((100*uploaded/fileLength).toInt())
    }
}

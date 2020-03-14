package com.sstudio.thebadminton.utils


import android.os.Handler
import android.os.Looper
import androidx.annotation.Nullable
import com.sstudio.thebadminton.menu3.mvp.upload.UploadView
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File

import java.io.FileInputStream

class ProgressRequestBody(private var file: File, private var listener: UploadView): RequestBody() {

    private val DEFAULT_BUFFER_SIZE = 4096

    @Nullable
    override fun contentType(): MediaType? {
        return MediaType.parse("video/*")
    }

    override fun writeTo(sink: BufferedSink) {
        val fileLength = file.length()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val fileInputStream = FileInputStream(file)
        var uploaded = 0

        try {
            var read: Int? = null
            val handler = Handler(Looper.getMainLooper())
            while ({ read = fileInputStream.read(buffer); read }() != -1) {
                handler.post(ProgressUpdater(uploaded, fileLength, listener))
                read?.let {
                    uploaded += it
                    sink.write(buffer, 0, it)
                }

            }
        }finally {
            fileInputStream.close()
        }
    }

    override fun contentLength(): Long {
        return file.length()
    }
}
package com.sstudio.thebadminton.menu3.mvp.upload

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Base64
import android.util.Log
//import com.ipaulpro.afilechooser.utils.FileUtils
import com.sstudio.thebadminton.model.Video
import com.sstudio.thebadminton.remote.IUploadAPI
import com.sstudio.thebadminton.remote.RetrofitClient
import com.sstudio.thebadminton.utils.ProgressRequestBody
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.ByteArrayOutputStream
import android.provider.MediaStore
import android.graphics.drawable.Drawable
import android.provider.Settings
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.request.RequestOptions
import com.sstudio.thebadminton.menu3.VideoActivity
import com.sstudio.thebadminton.model.CallbackID


class UploadPresenterImpl(private val context: Context, private val uploadView: UploadView):
    UploadPresenter {

    private val apiServices = RetrofitClient.getClient().create(IUploadAPI::class.java)
    private var videoList: List<Video>? = null

    override fun getData(){
        val call = apiServices.getData()
        call.enqueue(object : Callback<List<Video>> {
            override fun onResponse(call: Call<List<Video>>, response: Response<List<Video>>) {
                videoList = response.body()
                videoList?.let { uploadView.showVideoList(it) }
            }

            override fun onFailure(call: Call<List<Video>>, t: Throwable?) {
                t?.message?.let { uploadView.toast(it) }
                uploadView.dismissDialog()
            }
        })
    }
    override fun insertData(key: String, name: String, title: String, overview: String, selectedFileUri: Uri?) {
        uploadView.showDialog()
        val myOptions = RequestOptions()
            .override(100, 100)
        Glide.with(context)
            .asBitmap()
            .apply(myOptions)
            .load(selectedFileUri)
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    insertdt(key, name, title, overview, selectedFileUri, resource)
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                }
            })
    }

    private fun insertVid(id: String, file: File) {
        Log.d("taggUploadInsert", id)
        val requestFile = ProgressRequestBody(file, uploadView)
        val body = MultipartBody.Part.createFormData("uploaded_file", "$id.${file.extension}", requestFile)
        uploadView.showDialog()

        Thread(Runnable {
            apiServices.uploadFile(body)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        uploadView.dismissDialog()
                        uploadView.toast("Uploaded")
                        context.startActivity(Intent(context, VideoActivity::class.java))
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        uploadView.dismissDialog()
                        uploadView.toast(t.message+" onFailure.uploadFile(body)")
                    }
                })

        }).start()
    }


    private fun insertdt(key: String, name: String, title: String, overview: String, selectedFileUri: Uri?, bitmap: Bitmap?){
//        val androidId= Settings.Secure.getString(
//            context.contentResolver,
//            Settings.Secure.ANDROID_ID)
//        val file = FileUtils.getFile(context, selectedFileUri)
//        Thread(Runnable {
//            apiServices.insertData(key, name, title, overview, file.extension, getStringImage(bitmap as Bitmap), androidId)
////            apiServices.insertData("insert", "namaa", "judul", "desk", "videoUrl", "aa")
////            apiServices.insertComment("52", "sh", "ssss")
//                .enqueue(object : Callback<CallbackID> {
//                    override fun onResponse(call: Call<CallbackID>, response: Response<CallbackID>) {
//                        val id = response.body()?.id
//                        id?.let { insertVid(it, file) }
//                    }
//
//                    override fun onFailure(call: Call<CallbackID>, t: Throwable) {
//                        uploadView.dismissDialog()
//                        uploadView.toast(t.message + "onFailure.insertData")
//                    }
//                })
//        }).start()
    }

    private fun getStringImage(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

    override fun deleteVideo(id: String, videoUrl: String) {
        val dialog = android.app.AlertDialog.Builder(context)
        dialog.setMessage("Apakah anda yakin hapus video?")
        dialog.setPositiveButton("Ya") { dialog, which ->
            dialog.dismiss()
            Thread(Runnable {
                apiServices.deleteVideo(id, videoUrl)
                    .enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            uploadView.toast("Video telah dihapus")
                            context.startActivity(Intent(context, VideoActivity::class.java))
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            uploadView.toast(t.message+" onFailure.uploadFile(body)")
                        }
                    })

            }).start()
        }
        dialog.setNegativeButton(
            "Batal"
        ) { dialog, _ ->
            dialog.dismiss()
        }
        dialog.show()
//        Log.d("taggpres", "aaa $videoUrl")

    }
}
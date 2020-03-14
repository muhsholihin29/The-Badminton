package com.sstudio.thebadminton.menu3.mvp.comment

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.sstudio.thebadminton.Common
import com.sstudio.thebadminton.menu3.VideoActivity
import com.sstudio.thebadminton.model.Comment
import com.sstudio.thebadminton.model.Video
import com.sstudio.thebadminton.remote.IUploadAPI
import com.sstudio.thebadminton.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentPresenterImpl(private val context: Context, private val detailView: DetailView): CommentPresenter {
    private val apiServices = RetrofitClient.getClient().create(IUploadAPI::class.java)
    private var commentList: List<Comment>? = null

    override fun getComment(idVideo: String) {
        val call = apiServices.getComment(idVideo)
        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                commentList = response.body()
                commentList?.let { detailView.showComment(it) }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable?) {
                detailView.toast(t?.message as String)
            }
        })
    }

    override fun addComment(idVideo: String, name: String, comment: String) {
//        Log.d("taggAddCom", androidId+comment+idVideo)
        var mName = name
        if (Common.isCoach){
            mName = Common.coach
        }
        Thread(Runnable {
            apiServices.insertComment(idVideo, mName, comment)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        detailView.refreshComment()
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
//                        uploadView.dismissDialog()
                        detailView.toast(t.message + "onFail.add comment")
                    }
                })
        }).start()
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
                            detailView.toast("Video telah dihapus")
                            context.startActivity(Intent(context, VideoActivity::class.java))
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            detailView.toast(t.message+" onFailure.uploadFile(body)")
                        }
                    })

            }).start()
        }
        dialog.setNegativeButton(
            "Batal"
        ) { dialog, _ ->
            dialog.dismiss()
            return@setNegativeButton
        }
        dialog.show()
//        Log.d("taggpres", "aaa $videoUrl")

    }

    override fun deleteComment(id: String) {
        Thread(Runnable {
            apiServices.deleteComment(id)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        detailView.toast("Komentar telah dihapus")
                        detailView.refreshComment()
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        detailView.toast(t.message+" onFailure.delete comment)")
                    }
                })
        }).start()
    }

    override fun dialogCommentOption(id: String) {
        val dialogItems = arrayOf<CharSequence>("Hapus")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("")
        builder.setItems(dialogItems) { _, i ->
            when (i){
                0 -> deleteComment(id)
            }
        }
        builder.show()
    }

    override fun dialogVideoOption(id: String, videoUrl: String) {
        val dialogItems = arrayOf<CharSequence>("Hapus")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("")
        builder.setItems(dialogItems) { _, i ->
            when (i){
                0 -> deleteVideo(id, videoUrl)
            }
        }
        builder.show()
    }
}
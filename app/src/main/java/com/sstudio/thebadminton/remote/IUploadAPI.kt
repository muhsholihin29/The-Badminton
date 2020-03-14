package com.sstudio.thebadminton.remote

import android.net.Uri
import com.sstudio.thebadminton.model.CallbackID
import com.sstudio.thebadminton.model.Comment
import com.sstudio.thebadminton.model.Note
import com.sstudio.thebadminton.model.Video
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface IUploadAPI {
    @Multipart
    @POST("upload.php")
    fun uploadFile(@Part file: MultipartBody.Part): Call<Void>

    @POST("get.php")
    fun getData(): Call<List<Video>>

    @FormUrlEncoded
    @POST("insert.php")
    fun insertData(
        @Field("key") key: String,
        @Field("name") name: String,
        @Field("title") title: String,
        @Field("overview") overview: String,
        @Field("videoUrl") link: String,
        @Field("thumbnailUrl") thumbnail: String,
        @Field("androidId") androidId: String
    ): Call<CallbackID>

    @FormUrlEncoded
    @POST("delete_video.php")
    fun deleteVideo(
        @Field("id") id: String,
        @Field("videoUrl") videoUrl: String
    ): Call<Void>

    @FormUrlEncoded
    @POST("get_comment.php")
    fun getComment(
        @Field("idVideo") idVideo: String
    ): Call<List<Comment>>

    @FormUrlEncoded
    @POST("insert_comment.php")
    fun insertComment(
        @Field("idVideo") idVideo: String,
        @Field("name") name: String,
        @Field("comment") comment: String
    ): Call<Void>

    @FormUrlEncoded
    @POST("delete_comment.php")
    fun deleteComment(
        @Field("id") id: String
    ): Call<Void>

    @FormUrlEncoded
    @POST("get_note.php")
    fun getNote(
        @Field("androidId") androidId: String
    ): Call<List<Note>>

    @FormUrlEncoded
    @POST("insert_note.php")
    fun insertNote(
        @Field("title") title: String,
        @Field("note") note: String,
        @Field("androidId") androidId: String
    ): Call<Void>

    @FormUrlEncoded
    @POST("delete_note.php")
    fun deleteNote(
        @Field("id") id: String
    ): Call<Void>

    @FormUrlEncoded
    @POST("update_note.php")
    fun updateNote(
        @Field("id") id: String,
        @Field("title") title: String,
        @Field("note") note: String
    ): Call<Void>
}
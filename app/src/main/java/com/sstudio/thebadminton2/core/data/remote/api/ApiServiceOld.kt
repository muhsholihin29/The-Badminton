package com.sstudio.thebadminton2.core.data.remote.api

import com.sstudio.thebadminton2.core.data.remote.response.VideoResponse
import com.sstudio.thebadminton2.core.domain.model.Comment
import com.sstudio.thebadminton2.core.domain.model.Note
import com.sstudio.thebadminton2.core.domain.model.Video
import com.sstudio.thebadminton2.model.CallbackID
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceOld {

    @FormUrlEncoded
    @Multipart
    @POST("insert.php")
    fun insertVideo(
        @Part("image") image: MultipartBody.Part,
        @Part("video") video: MultipartBody.Part,
        @Field("key") key: String,
        @Field("name") name: String,
        @Field("title") title: String,
        @Field("overview") overview: String,
        @Field("androidId") androidId: String
    ): Void

    @POST("get.php")
    fun getVideos(): List<VideoResponse>

    @FormUrlEncoded
    @POST("delete_video.php")
    fun deleteVideox(
        @Field("id") id: String,
        @Field("videoUrl") videoUrl: String
    ): Void

    fun insertCommentx(
        @Field("idVideo") idVideo: String,
        @Field("name") name: String,
        @Field("comment") comment: String
    ): Void

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
        @Field("idVideo") idVideo: Int
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
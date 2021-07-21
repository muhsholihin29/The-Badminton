package com.sstudio.thebadminton2.core.data.remote.api

import com.sstudio.thebadminton2.core.data.remote.response.CommentResponse
import com.sstudio.thebadminton2.core.data.remote.response.MetaResponse
import com.sstudio.thebadminton2.core.data.remote.response.NoteResponse
import com.sstudio.thebadminton2.core.data.remote.response.VideoResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST("video")
    suspend fun insertVideo(
        @Part image: MultipartBody.Part,
        @Part video: MultipartBody.Part,
        @Part("name") name: String,
        @Part("title") title: String,
        @Part("overview") overview: String,
        @Part("androidId") androidId: String
    ): MetaResponse

    @GET("video/list")
    suspend fun getVideos(): VideoResponse

    @DELETE("video/{id}")
    suspend fun deleteVideo(@Path("id") id: Int): MetaResponse

    @GET("video/{idVideo}/comment")
    suspend fun getComment(@Path("idVideo") idVideo: Int): CommentResponse

    @FormUrlEncoded
    @POST("video/{id}/comment")
    suspend fun insertComment(
        @Path("id")  idVideo: Int,
        @Field("name") name: String,
        @Field("comment") comment: String
    ): MetaResponse

    @DELETE("video/comment/{id}")
    suspend fun deleteComment(id: Int): MetaResponse

    @GET("note/{androidId}/list")
    suspend fun getNote(@Path("androidId") androidId: String): NoteResponse

    @FormUrlEncoded
    @POST("note/{androidId}")
    suspend fun insertNote(
        @Field("title") title: String,
        @Field("note") note: String,
        @Path("androidId") androidId: String
    ): MetaResponse

    @DELETE("note/{id}")
    suspend fun deleteNote(@Path("id") id: Int): MetaResponse

    @FormUrlEncoded
    @POST("note/update/{id}")
    suspend fun updateNote(
        @Path("id") id: Int,
        @Field("title") title: String,
        @Field("note") note: String
    ): MetaResponse
}
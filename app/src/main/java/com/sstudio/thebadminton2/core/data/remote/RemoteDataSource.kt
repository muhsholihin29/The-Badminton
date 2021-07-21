package com.sstudio.thebadminton2.core.data.remote

import android.util.Log
import com.sstudio.thebadminton2.core.data.remote.api.ApiService
import com.sstudio.thebadminton2.core.data.remote.network.ApiResponse
import com.sstudio.thebadminton2.core.data.remote.response.CommentResponse
import com.sstudio.thebadminton2.core.data.remote.response.MetaResponse
import com.sstudio.thebadminton2.core.data.remote.response.NoteResponse
import com.sstudio.thebadminton2.core.data.remote.response.VideoResponse
import com.sstudio.thebadminton2.core.domain.model.Comment
import com.sstudio.thebadminton2.core.domain.model.Note
import com.sstudio.thebadminton2.core.domain.model.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getVideos(): Flow<ApiResponse<VideoResponse>> {
        return flow {

            try {

                val response = apiService.getVideos()

                if (response.data.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun insertVideo(video: Video, fileVideo: File, fileThumbnail: File): Flow<ApiResponse<MetaResponse>> {
        return flow {
            try {
                val fileThumbnailPart = MultipartBody.Part.createFormData("thumbnail", fileThumbnail.name, RequestBody.create(
                    "image/*".toMediaTypeOrNull(), fileThumbnail));

                val fileVideoPart = MultipartBody.Part.createFormData("video", fileVideo.name, RequestBody.create(
                    "video/*".toMediaTypeOrNull(), fileVideo))

                val response = apiService.insertVideo(
                    video = fileVideoPart,
                    image = fileThumbnailPart,
                    name = video.name,
                    title = video.title,
                    overview = video.overview,
                    androidId = video.androidId ?: "0"
                )
                emit(ApiResponse.Success(response))
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }
    fun deleteVideo(videoId: Int): Flow<ApiResponse<MetaResponse>> {
        return flow {
            try {
                val response = apiService.deleteVideo(videoId)
                emit(ApiResponse.Success(response))
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    fun getComments(videoId: Int): Flow<ApiResponse<CommentResponse>> = flow {
        try {
            val response = apiService.getComment(videoId)
            if (response.data.isNotEmpty()){
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e : Exception){
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    fun insertComment(videoId: Int, comment: Comment): Flow<ApiResponse<MetaResponse>> = flow {
        try {
            val response = apiService.insertComment(idVideo = videoId, name = comment.name, comment = comment.comment)
                emit(ApiResponse.Success(response))
        } catch (e : Exception){
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    fun deleteComment(commentId: Int): Flow<ApiResponse<MetaResponse>> = flow {
        try {
            val response = apiService.deleteComment(id = commentId)
            emit(ApiResponse.Success(response))
        } catch (e : Exception){
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    fun getNote(androidId: String): Flow<ApiResponse<NoteResponse>> = flow {
        try {
            val response = apiService.getNote(androidId)
            if (response.data.isNotEmpty()){
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e : Exception){
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    fun insertNote(note: Note): Flow<ApiResponse<MetaResponse>> = flow {
        try {
            val response = apiService.insertNote(title = note.title, note = note.note, androidId = note.androidId)
            emit(ApiResponse.Success(response))
        } catch (e : Exception){
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    fun deleteNote(noteId: Int): Flow<ApiResponse<MetaResponse>> = flow {
        try {
            val response = apiService.deleteNote(noteId)
            emit(ApiResponse.Success(response))
        } catch (e : Exception){
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    fun updateNote(note: Note): Flow<ApiResponse<MetaResponse>> = flow {
        try {
            val response = apiService.updateNote(id = note.id, title = note.title, note = note.note)
            emit(ApiResponse.Success(response))
        } catch (e : Exception){
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)
}
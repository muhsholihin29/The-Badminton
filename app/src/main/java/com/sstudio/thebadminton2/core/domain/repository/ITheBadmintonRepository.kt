package com.sstudio.thebadminton2.core.domain.repository

import android.net.Uri
import com.sstudio.thebadminton2.core.data.Resource
import com.sstudio.thebadminton2.core.domain.model.Comment
import com.sstudio.thebadminton2.core.domain.model.Note
import com.sstudio.thebadminton2.core.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface ITheBadmintonRepository {
//    fun uploadFile(@Part file: MultipartBody.Part): Call<Void>


    fun getVideos(): Flow<Resource<List<Video>>>

    fun insertVideo(video: Video, videoUri: Uri): Flow<Resource<String>>
    fun deleteVideo(videoId: Int): Flow<Resource<Void>>
    fun getComments(videoId: Int): Flow<Resource<List<Comment>>>
    fun insertComment(videoId: Int, comment: Comment): Flow<Resource<Void>>
    fun deleteComment(commentId: Int): Flow<Resource<Void>>

    fun getNote(androidId: String): Flow<Resource<List<Note>>>

    fun insertNote(note: Note): Flow<Resource<Void>>

    fun deleteNote(noteId: Int): Flow<Resource<Void>>

    fun updateNote(note: Note): Flow<Resource<Void>>
}
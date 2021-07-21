package com.sstudio.thebadminton2.core.domain.usecase

import android.net.Uri
import com.sstudio.thebadminton2.core.data.Resource
import com.sstudio.thebadminton2.core.domain.model.Comment
import com.sstudio.thebadminton2.core.domain.model.Note
import com.sstudio.thebadminton2.core.domain.model.Video
import com.sstudio.thebadminton2.core.domain.repository.ITheBadmintonRepository
import kotlinx.coroutines.flow.Flow

class TheBadmintonInteractor(private val iTheBadmintonRepository: ITheBadmintonRepository): TheBadmintonUseCase {
    override fun getVideos(): Flow<Resource<List<Video>>> = iTheBadmintonRepository.getVideos()

    override fun insertVideo(video: Video, videoUri: Uri): Flow<Resource<String>> =
        iTheBadmintonRepository.insertVideo(video, videoUri)

    override fun deleteVideo(videoId: Int): Flow<Resource<Void>> =
        iTheBadmintonRepository.deleteVideo(videoId)

    override fun getComments(videoId: Int): Flow<Resource<List<Comment>>> =
        iTheBadmintonRepository.getComments(videoId)

    override fun insertComment(videoId: Int, comment: Comment): Flow<Resource<Void>> =
        iTheBadmintonRepository.insertComment(videoId, comment)

    override fun deleteComment(commentId: Int): Flow<Resource<Void>> =
        iTheBadmintonRepository.deleteComment(commentId)

    override fun getNote(androidId: String): Flow<Resource<List<Note>>> =
        iTheBadmintonRepository.getNote(androidId)

    override fun insertNote(note: Note): Flow<Resource<Void>> =
        iTheBadmintonRepository.insertNote(note)

    override fun deleteNote(noteId: Int): Flow<Resource<Void>> =
        iTheBadmintonRepository.deleteNote(noteId)

    override fun updateNote(note: Note): Flow<Resource<Void>> =
        iTheBadmintonRepository.updateNote(note)
}
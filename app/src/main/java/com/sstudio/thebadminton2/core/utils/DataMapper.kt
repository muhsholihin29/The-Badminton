package com.sstudio.thebadminton2.core.utils

import com.sstudio.thebadminton2.core.data.remote.response.CommentResponse
import com.sstudio.thebadminton2.core.data.remote.response.NoteResponse
import com.sstudio.thebadminton2.core.data.remote.response.VideoResponse
import com.sstudio.thebadminton2.core.domain.model.Comment
import com.sstudio.thebadminton2.core.domain.model.Note
import com.sstudio.thebadminton2.core.domain.model.Video

object DataMapper {
    fun mapCommentResponseToDomain(input: CommentResponse.Data) =
        Comment(id = input.id, name = input.name ?: "", comment = input.comment ?: "")

    fun mapNoteResponseToDomain(input: NoteResponse.Data) =
        Note(id = input.id, title = input.title ?: "", androidId = input.androidId, note = input.note ?: "")

    fun mapVideoResponseToDomain(input: VideoResponse.Data) =
        Video(
            id = input.id,
            title = input.title ?: "",
            name = input.name ?: "",
            overview = input.overview ?: "",
            videoUrl = input.videoUrl ?: "",
            thumbnailUrl = input.thumbnailUrl ?: "",
            androidId = input.androidId
        )
}
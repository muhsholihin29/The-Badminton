package com.sstudio.thebadminton.menu3.mvp.comment

import com.sstudio.thebadminton.model.Comment

interface CommentPresenter {
    fun getComment(idVideo: String)
    fun addComment(idVideo: String, name: String, comment: String)
    fun deleteComment(id: String)
    fun deleteVideo(id: String, videoUrl: String)
    fun dialogCommentOption(id: String)
    fun dialogVideoOption(id: String, videoUrl: String)
}
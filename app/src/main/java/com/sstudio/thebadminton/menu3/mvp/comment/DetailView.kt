package com.sstudio.thebadminton.menu3.mvp.comment

import com.sstudio.thebadminton.model.Comment

interface DetailView {
    fun showComment(comment: List<Comment>)
    fun refreshComment()
    fun toast(text: String)
}
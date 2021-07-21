package com.sstudio.thebadminton2.core.data.remote.response

data class CommentResponse(
    val meta: Meta,
    val data: List<Data>
){
    data class Data(
        val id: Int,
        val comment: String?,
        val idVideo: Int,
        val name: String?
    )
}
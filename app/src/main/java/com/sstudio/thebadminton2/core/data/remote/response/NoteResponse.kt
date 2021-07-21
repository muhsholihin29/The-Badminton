package com.sstudio.thebadminton2.core.data.remote.response

data class NoteResponse(
    val meta: Meta,
    val data: List<Data>
){
    data class Data(
        val id: Int,
        val androidId: String = "",
        val note: String?,
        val title: String?
    )
}
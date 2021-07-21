package com.sstudio.thebadminton2.core.data.remote.response

data class VideoResponse(
    val data: List<Data>,
    val metaResponse: MetaResponse
){
    data class Data(
        val id: Int,
        val androidId: String = "",
        val name: String,
        val title: String,
        val overview: String,
        val thumbnailUrl: String,
        val videoUrl: String
    )
}
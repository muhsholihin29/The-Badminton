package com.sstudio.thebadminton.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Video(
    var title: String = "",
    var name: String = "",
    var overview: String = "",
    var videoUrl: String = "",
    var thumbnailUrl: String = "",
    var androidId: String? = "",
    var id: String? = ""
):Parcelable
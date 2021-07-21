package com.sstudio.thebadminton2.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Video(
    var id: Int = 0,
    var title: String = "",
    var name: String = "",
    var overview: String = "",
    var videoUrl: String = "",
    var thumbnailUrl: String = "",
    var androidId: String = ""
):Parcelable
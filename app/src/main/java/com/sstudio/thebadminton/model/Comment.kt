package com.sstudio.thebadminton.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Comment(
    var id: String,
    var name: String,
    var comment: String

): Parcelable
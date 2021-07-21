package com.sstudio.thebadminton2.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Comment(
    var id: Int,
    var name: String,
    var comment: String

): Parcelable
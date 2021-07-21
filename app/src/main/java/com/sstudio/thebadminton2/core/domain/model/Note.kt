package com.sstudio.thebadminton2.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Note(
    var id: Int = 0,
    var title: String = "",
    var androidId: String = "",
    var note: String = ""
): Parcelable
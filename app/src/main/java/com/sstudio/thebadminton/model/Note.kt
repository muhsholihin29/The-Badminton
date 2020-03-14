package com.sstudio.thebadminton.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Note(
    var id: String = "",
    var title: String = "",
    var androidId: String = "",
    var note: String = ""
): Parcelable
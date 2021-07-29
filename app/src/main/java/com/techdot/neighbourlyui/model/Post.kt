package com.techdot.neighbourlyui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val userImage: Int,
    val userName: String,
    val userLocation: String,
    val question: String,
    val commenterImage: Int,
    val commenterName: String,
    val answer: String,
    val numberOfAnswers: Int,
    val postImage: Int?,
    val imageCaption: String?
): Parcelable {
}
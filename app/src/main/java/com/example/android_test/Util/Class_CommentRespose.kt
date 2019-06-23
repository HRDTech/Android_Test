package com.example.android_test.Util

import com.google.gson.annotations.SerializedName

data class Class_CommentRespose(
    @SerializedName("body") val body: String,
    @SerializedName("email") val email: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("postId") val postId: Int
)
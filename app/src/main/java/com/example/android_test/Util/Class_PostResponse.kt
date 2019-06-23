package com.example.android_test.Util

import com.google.gson.annotations.SerializedName

data class Class_PostResponse(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)
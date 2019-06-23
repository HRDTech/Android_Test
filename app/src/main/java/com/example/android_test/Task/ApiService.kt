package com.example.android_test.Task

import com.example.android_test.Util.Class_CommentRespose
import com.example.android_test.Util.Class_PostResponse
import com.example.android_test.Util.Class_UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface ApiService {
    @GET("posts")
    fun getJsonData() : Observable <List<Class_PostResponse>>

    @GET("users?")
    fun getJsonUser(@Query("id") url: String):Observable <Class_UserResponse>

    @GET
    fun getJsonComment(@Url url: String) : Observable<List<Class_CommentRespose>>
}
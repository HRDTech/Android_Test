package com.example.android_test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.android_test.Adapter.Class_CommentAdapter
import com.example.android_test.Task.ApiService
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CommentActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        val bundle = intent.extras
        val name1 = bundle.get("postId")
        val commentId: String = name1.toString()

        recyclerView = findViewById(R.id.recycleViewComment)

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

        val postsApi = retrofit.create(ApiService::class.java)
        var response = postsApi.getJsonComment("comments?postId=" + commentId)

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe {

            var adapter = Class_CommentAdapter(this, it)
            val layoutManager = LinearLayoutManager(applicationContext)
            recyclerView!!.layoutManager = layoutManager
            recyclerView!!.adapter = adapter
        }
    }
}

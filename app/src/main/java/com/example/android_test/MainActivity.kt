package com.example.android_test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.android_test.Adapter.Class_Adapter
import com.example.android_test.Task.ApiService
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOError
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.android_recycler_view) as RecyclerView

        try {

            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()
            val postsApi = retrofit.create(ApiService::class.java)
            var response = postsApi.getJsonData()

            response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe {
                if (it.isNotEmpty()) {
                    var adapter = Class_Adapter(this, it)
                    val layoutManager = LinearLayoutManager(applicationContext)
                    recyclerView!!.layoutManager = layoutManager
                    recyclerView!!.adapter = adapter
                } else {
                    Toast.makeText(this, "Error en la conexión", Toast.LENGTH_SHORT).show()
                }
            }
        }catch (e: HttpException){
            Toast.makeText(this, e.message(), Toast.LENGTH_SHORT).show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}

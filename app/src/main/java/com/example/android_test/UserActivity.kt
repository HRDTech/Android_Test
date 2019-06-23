package com.example.android_test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.android_test.Task.ApiService
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import android.view.View.OnClickListener as OnClickListener1

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val bundle = intent.extras
        val name = bundle.get("userId")
        val userId: String = name.toString()

        val name1 = bundle.get("postId")
        val commentId: String = name1.toString()

        val textName:TextView = findViewById(R.id.textName)
        val textUserName:TextView = findViewById(R.id.textUserName)
        val textEmail:TextView = findViewById(R.id.textEmail)
        val textStreet:TextView = findViewById(R.id.textStreet)
        val textSuite: TextView = findViewById(R.id.textSuite)
        val textCity: TextView = findViewById(R.id.textCity)
        val textZipCode: TextView = findViewById(R.id.textZipCode)
        val textLat: TextView = findViewById(R.id.textLat)
        val textLng: TextView = findViewById(R.id.textLng)
        val textPhone: TextView = findViewById(R.id.textPhone)
        val textWebSite: TextView = findViewById(R.id.textWebsite)
        val textComName: TextView = findViewById(R.id.textCompName)
        val textCatchPhrase: TextView = findViewById(R.id.textCatchPhrase)
        val textBS: TextView = findViewById(R.id.textBS)


        val btnComment:Button = findViewById(R.id.buttonUser)
        btnComment.setOnClickListener {
            val launchAct = Intent(this, CommentActivity::class.java)
            launchAct.putExtra("postId", commentId)
            startActivity(launchAct)
        }
        try {

            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()

            val postsApi = retrofit.create(ApiService::class.java)
            val response = postsApi.getJsonUser(userId)

            response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe {
                textName.text = it.name
                textUserName.text = it.username
                textEmail.text = it.email
                textStreet.text = it.address.street
                textSuite.text = it.address.suite
                textCity.text = it.address.city
                textZipCode.text = it.address.zipcode
                textLat.text = it.address.geo.lat
                textLng.text = it.address.geo.lng
                textPhone.text = it.phone
                textWebSite.text = it.website
                textComName.text = it.company.name
                textCatchPhrase.text = it.company.catchPhrase
                textBS.text = it.company.bs


            }
        }catch (e: HttpException){
            Toast.makeText(this, e.message(), Toast.LENGTH_SHORT).show()
        }

    }


}
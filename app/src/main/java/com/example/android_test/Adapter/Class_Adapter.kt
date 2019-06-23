package com.example.android_test.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android_test.R
import com.example.android_test.UserActivity
import com.example.android_test.Util.Class_PostResponse

class Class_Adapter(internal var context: Context, internal var postList: List<Class_PostResponse>): RecyclerView.Adapter<Class_ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Class_ViewHolder {
        val lastView = LayoutInflater.from(parent.context).inflate(R.layout.layout_post_list, parent, false)
        return Class_ViewHolder(lastView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: Class_ViewHolder, position: Int) {
        holder.titleText.text = postList[position].title
        holder.bodyText.text = postList[position].body
        holder.layout.setOnClickListener {
            val launchAct = Intent(context, UserActivity::class.java)
            launchAct.putExtra("userId", postList[position].userId)
            launchAct.putExtra("postId", postList[position].id)
            context.startActivity(launchAct)
        }
    }
}
package com.example.android_test.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android_test.R
import com.example.android_test.Util.Class_CommentRespose

class Class_CommentAdapter(internal var context: Context, internal var postList: List<Class_CommentRespose>): RecyclerView.Adapter<Class_CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Class_CommentViewHolder {
        val lastView = LayoutInflater.from(parent.context).inflate(R.layout.layout_comment_list, parent, false)
        return Class_CommentViewHolder(lastView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: Class_CommentViewHolder, position: Int) {
        holder.titleText.text = postList[position].name
        holder.emailText.text = postList[position].email
        holder.commentText.text = postList[position].body

    }

}
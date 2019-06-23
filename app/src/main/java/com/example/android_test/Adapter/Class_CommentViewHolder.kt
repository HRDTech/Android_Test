package com.example.android_test.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.android_test.R
import kotlinx.android.synthetic.main.layout_comment_list.view.*

class Class_CommentViewHolder(lastView: View): RecyclerView.ViewHolder(lastView)  {
     var layout: View
     var titleText: TextView
     var emailText: TextView
     var commentText: TextView

    init {
        layout = lastView.findViewById(R.id.comment_layout)
        titleText = lastView.findViewById(R.id.textCommentName)
        emailText = lastView.findViewById(R.id.textCommentEmail)
        commentText = lastView.findViewById(R.id.textCommentComment)
    }

}
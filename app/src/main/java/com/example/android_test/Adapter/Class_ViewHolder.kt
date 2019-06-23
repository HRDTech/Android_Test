package com.example.android_test.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.android_test.R

class Class_ViewHolder(lastView: View):RecyclerView.ViewHolder(lastView) {
    var layout: View
    var titleText: TextView
    var bodyText: TextView

    init {
        layout = lastView.findViewById(R.id.android_layout)
        titleText = lastView.findViewById(R.id.title) as TextView
        bodyText = lastView.findViewById(R.id.body) as TextView

    }
}
package com.example.githubapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("app:hideIfNull")
fun hideIfNull(view: View, string: String?) {
    view.visibility = if (string.isNullOrEmpty()) View.GONE else View.VISIBLE
}

@BindingAdapter("app:scr")
fun uploadImage(view: ImageView, string: String?) {
    Picasso.with(view.context).load(string).into(view)
}



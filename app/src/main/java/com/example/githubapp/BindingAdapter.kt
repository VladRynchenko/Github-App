package com.example.githubapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.text.DecimalFormat


@BindingAdapter("app:hideIfNull")
fun hideIfNull(view: View, string: String?) {
    view.visibility = if (string.isNullOrEmpty()) View.GONE else View.VISIBLE
}

@BindingAdapter("app:scr")
fun uploadImage(view: ImageView, string: String?) {
    Picasso.with(view.context).load(string).into(view)
}

@BindingAdapter("app:roundingCount")
fun roundCount(view: TextView, int: Int) {
    val numberFormat = DecimalFormat("###.#")
    view.text = when (int) {
        in 0..1000 -> numberFormat.format(int)
        in 1000..1000000 -> numberFormat.format(int.toFloat() / 1000) + view.context.getString(R.string.thousands)
        else -> numberFormat.format(int.toFloat() / 1000000) + view.context.getString(R.string.millions)
    }
}



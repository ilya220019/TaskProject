package com.example.taskproject.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taskproject.R

fun ImageView.loadImage(url: String?) {
    Glide.with(this).load(url).placeholder(R.drawable.ic_home_black_24dp).into(this)
}
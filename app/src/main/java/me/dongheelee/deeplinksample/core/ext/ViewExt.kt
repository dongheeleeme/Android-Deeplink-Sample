package me.dongheelee.deeplinksample.core.ext

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String) = Glide.with(this.rootView).load(url).into(this)

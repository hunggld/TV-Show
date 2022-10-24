package com.sildev.tvshows.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sildev.tvshows.R

fun ImageView.setResource(resource: String, context: Context) {
    Glide.with(context)
        .load(resource)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(RADIUS_TV_SHOW_IMAGE)))
        .placeholder(R.drawable.ic_tv)
        .error(R.drawable.ic_tv)
        .into(this)
}

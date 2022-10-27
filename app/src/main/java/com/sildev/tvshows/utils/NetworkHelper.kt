package com.sildev.tvshows.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AlertDialog
import com.sildev.tvshows.R

object NetworkHelper {

    fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivityManager.allNetworkInfo
        info.forEach {
            if (it.state == NetworkInfo.State.CONNECTED) return true
        }
        return false
    }

    fun getNetworkAlertDialog(context: Context): AlertDialog.Builder {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(context.getString(R.string.warning))
        alertDialog.setMessage(context.getString(R.string.alert_internet))
        alertDialog.setPositiveButton(context.getString(R.string.ok)) { _, _ -> }
        return alertDialog
    }
}

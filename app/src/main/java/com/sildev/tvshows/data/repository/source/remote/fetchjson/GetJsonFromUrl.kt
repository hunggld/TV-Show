package com.sildev.tvshows.data.repository.source.remote.fetchjson

import android.os.Handler
import android.os.Looper
import com.sildev.tvshows.data.repository.source.remote.OnResultListener
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GetJsonFromUrl<T>(
    private val urlString: String,
    private val keyEntity: String,
    private val listener: OnResultListener<T>,
    private val status: String
) {
    private val mExecutor: Executor = Executors.newSingleThreadExecutor()
    private val mHandler = Handler(Looper.getMainLooper())
    private var data: T? = null

    init {
        callAPI()
    }

    private fun callAPI() {
        mExecutor.execute {
            val responseJson =
                getJson(urlString)
            data = ParseDataWithJson().parseJsonToData(
                JSONObject(responseJson),
                keyEntity,
                status
            ) as? T
            mHandler.post {
                try {
                    data?.let { listener.onSuccess(it) }
                } catch (e: JSONException) {
                    listener.onError(e)
                }
            }
        }
    }

    private fun getJson(urlString: String): String {
        val url = URL(urlString)
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.run {
            connectTimeout = TIME_OUT
            readTimeout = TIME_OUT
            requestMethod = METHOD_GET
            doOutput = true
            connect()
        }
        val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))
        val stringBuilder = java.lang.StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        bufferedReader.close()
        httpURLConnection.disconnect()
        return stringBuilder.toString()
    }

    companion object {
        private const val TIME_OUT = 1000
        private const val METHOD_GET = "GET"
    }
}

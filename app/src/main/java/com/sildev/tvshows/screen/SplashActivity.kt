package com.sildev.tvshows.screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sildev.tvshows.R
import com.sildev.tvshows.utils.A_SECOND
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, A_SECOND)
    }
}

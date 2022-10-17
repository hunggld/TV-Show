package com.sildev.tvshows.screen

import android.content.Intent
import android.os.Bundle
import com.sildev.tvshows.databinding.ActivitySplashBinding
import com.sildev.tvshows.utils.A_SECOND
import com.sildev.tvshows.utils.base.BaseActivity
import java.util.*

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, A_SECOND)
    }
}

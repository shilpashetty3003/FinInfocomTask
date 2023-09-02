package com.example.fininfocomtask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.fininfocomtask.R
import com.example.fininfocomtask.databinding.ActivitySplashScreenBinding

class SplashScreen : BaseActivity<ActivitySplashScreenBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val fadeIn: Animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                binding.tvAppName.visibility = View.VISIBLE
            }
            override fun onAnimationEnd(animation: Animation) {
                Handler().postDelayed({
                    startActivity(Intent(this@SplashScreen, LoginScreen::class.java))
                    finish()
                }, 1000)
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })
        binding.tvAppName.startAnimation(fadeIn)

    }

    override fun getViewBinding(): ActivitySplashScreenBinding {
        return ActivitySplashScreenBinding.inflate(layoutInflater)
    }
}
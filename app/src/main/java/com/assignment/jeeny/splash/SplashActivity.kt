package com.assignment.jeeny.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.lifecycleScope
import com.assignment.jeeny.base.BaseActivity
import com.assignment.jeeny.main.MainActivity
import com.example.jeeny.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(3000)
            navigateToMainScreen()
        }
    }

    @MainThread
    fun navigateToMainScreen() = startActivity(Intent(this, MainActivity::class.java))
}
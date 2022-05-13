package com.assignment.jeeny.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jeeny.R

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}
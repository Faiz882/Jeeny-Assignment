package com.assignment.jeeny

import android.os.Bundle
import com.example.jeeny.R
import com.assignment.jeeny.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
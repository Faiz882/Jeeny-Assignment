package com.assignment.jeeny.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assignment.jeeny.model.GithubRepoModel
import com.example.jeeny.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val repo = intent.getParcelableExtra<GithubRepoModel>("repo")!!

        updateUi(repo)
    }

    private fun updateUi(repo: GithubRepoModel) {
//        TODO("update ui")
    }
}
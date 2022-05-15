package com.assignment.jeeny.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assignment.jeeny.base.BaseActivity
import com.assignment.jeeny.model.GithubRepoModel
import com.example.jeeny.R
import com.example.jeeny.databinding.ActivityDetailBinding

class RepositoryDetailActivity : BaseActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val repo = intent.getParcelableExtra<GithubRepoModel>("repo")!!

        updateUi(repo)
    }

    private fun updateUi(repo: GithubRepoModel) {
//        TODO("update ui")
    }
}
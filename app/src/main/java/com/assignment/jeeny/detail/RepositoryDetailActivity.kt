
package com.assignment.jeeny.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assignment.jeeny.base.BaseActivity
import com.assignment.jeeny.model.GithubRepoModel
import com.example.jeeny.R
import com.example.jeeny.databinding.ActivityRepoDetailBinding

class RepositoryDetailActivity : BaseActivity() {
    private val binding: ActivityRepoDetailBinding by lazy {
        ActivityRepoDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val repo = intent.getParcelableExtra<GithubRepoModel>("repo")!!

        updateUi(repo)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.ivBack.setOnClickListener{
            onBackPressed()
        }
    }

    private fun updateUi(repo: GithubRepoModel) {
        binding.repo = repo
    }
}


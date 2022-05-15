package com.assignment.jeeny.main

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.assignment.jeeny.base.BaseActivity
import com.assignment.jeeny.detail.DetailActivity
import com.assignment.jeeny.model.GithubRepoModel
import com.example.jeeny.R
import com.example.jeeny.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private val searchAdapter = GithubRepoAdapter()
    private val savedAdapter = GithubRepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
        initUiListener()
        setupSearch()
        setupSavedSearch()

        mainViewModel.error.observe(this) {
            if (it.isNullOrEmpty().not())
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initUi() {
        binding.rvSearch.adapter = searchAdapter
        binding.rvSaved.adapter = savedAdapter
    }

    private fun initUiListener() {
        fun openDetail(repo: GithubRepoModel){
            startActivity(
                Intent(this, DetailActivity::class.java).also {
                    it.putExtra("repo", repo)
                }
            )
        }

        searchAdapter.onItemClick = {
            mainViewModel.saveSearch(it)
            openDetail(it)
        }
        savedAdapter.onItemClick = {
            openDetail(it)
        }
    }

    private fun setupSearch() {
        binding.inSearchMode = false
        binding.etSearch.addTextChangedListener {
            binding.inSearchMode = it.isNullOrEmpty().not()
            if (it.isNullOrEmpty().not() && it.toString().length >= 3) {
                mainViewModel.searchRepo(it.toString())
            }
        }
        binding.etSearch.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                val search = binding.etSearch.text.toString()
                if (search.isNotEmpty()) mainViewModel.searchRepo(search)
                true
            } else
                false
        }

        mainViewModel.githubSearchResponse.observe(this)
        {
            binding.tvResultCount.text = getString(R.string.result_count, it.totalCount.toString())
            searchAdapter.submitList(it.items)
        }
    }

    private fun setupSavedSearch() {
        mainViewModel.getSavedSearch().observe(this) {
            savedAdapter.submitList(it)
        }
    }
}
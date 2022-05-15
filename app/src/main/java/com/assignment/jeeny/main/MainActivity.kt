package com.assignment.jeeny.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.assignment.jeeny.base.BaseActivity
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
    }

    private fun initUi() {
        binding.rvSearch.adapter = searchAdapter
        binding.rvSaved.adapter = savedAdapter
    }

    private fun initUiListener() {
        searchAdapter.onItemClick = {
            mainViewModel.saveSearch(it)
        }
        savedAdapter.onItemClick = {

        }
    }

    private fun setupSearch() {
        binding.inSearchMode = false
        binding.etSearch.addTextChangedListener {
            binding.inSearchMode = it.isNullOrEmpty().not()
            if (!it.isNullOrEmpty().not() && it.toString().length >= 3) {
                mainViewModel.searchRepo(it.toString())
            }
        }

        mainViewModel.githubSearchResponse.observe(this) {
            binding.tvResultCount.text = getString(R.string.result_count, it.totalCount.toString())
            searchAdapter.submitList(it.items)
        }
    }

    private fun setupSavedSearch() {
        mainViewModel.getSavedSearch().observe(this){
            savedAdapter.submitList(it)
        }
    }
}
package com.assignment.jeeny.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.assignment.jeeny.base.BaseActivity
import com.assignment.jeeny.detail.RepositoryDetailActivity
import com.assignment.jeeny.enums.Status
import com.assignment.jeeny.ext.*
import com.assignment.jeeny.model.GithubRepoModel
import com.assignment.jeeny.model.GithubSearchSearchResponse
import com.assignment.jeeny.utils.Constants
import com.example.jeeny.R
import com.example.jeeny.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainViewModel by viewModels<MainViewModel>()
    private val searchAdapter = GithubRepoAdapter()
    private val savedAdapter = GithubRepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUi()
        initUiListener()
        setupSearch()
        setupSavedSearch()
        setObserver()
    }

    private fun setObserver() {
        mainViewModel.githubSearchResponse.observe(this) {
            when(it.status){
                Status.LOADING->{
                    onResponseLoading()
                }
                Status.SUCCESS->{
                    binding.progressBar.hide()
                    val data = it.data?:return@observe
                    onResponseSuccess(data)
                }
                Status.ERROR->{
                    onResponseError(it.message)
                }
            }
        }
    }

    private fun onResponseLoading() {
        binding.progressBar.show()
    }

    private fun onResponseError(it: String?) {
        binding.progressBar.hide()
        val message = it ?: getString(R.string.something_went_wrong_please_try_again_later)
        showToast(message)
    }

    private fun onResponseSuccess(data: GithubSearchSearchResponse) {
        binding.tvResultCount.text = getString(R.string.result_count, data.totalCount.toString())
        searchAdapter.submitList(data.items)
    }

    private fun initUi() {
        binding.rvSearch.adapter = searchAdapter
        binding.rvSaved.adapter = savedAdapter
    }

    private fun initUiListener() {
        fun openDetail(repo: GithubRepoModel) {
            val bundle = Bundle()
            bundle.putParcelable(Constants.Repo, repo)
            startActivityBundle(RepositoryDetailActivity::class.java, bundle)
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

        binding.etSearch.onSearch {
            val search = binding.etSearch.text.toString()
            if (search.isNotEmpty()) {
                mainViewModel.searchRepo(search)
                hideKeyboard()
            }
        }
    }

    private fun setupSavedSearch() {
        mainViewModel.getSavedSearch().observe(this) {
            savedAdapter.submitList(it)
        }
    }
}
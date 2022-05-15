package com.assignment.jeeny.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.jeeny.base.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MainRepo
) : ViewModel() {

    fun searchRepo(search: String) = viewModelScope.launch {
        repo.searchRepo(search)
    }
}
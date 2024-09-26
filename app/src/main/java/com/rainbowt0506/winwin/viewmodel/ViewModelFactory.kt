package com.rainbowt0506.winwin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rainbowt0506.winwin.repository.PatternRepository
import com.rainbowt0506.winwin.viewmodel.PatternViewModel

class ViewModelFactory(private val repository: PatternRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatternViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PatternViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

package com.rainbowt0506.winwin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rainbowt0506.winwin.model.HueGroup
import com.rainbowt0506.winwin.repository.PatternRepository

class PatternViewModel(private val repository: PatternRepository) : ViewModel() {

    private val _patterns = MutableLiveData<List<HueGroup>>()
    val patterns: LiveData<List<HueGroup>> = _patterns

    fun loadPatterns() {
        _patterns.value = repository.getPatterns()
    }
}

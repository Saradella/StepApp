package com.pl.sszczc.stepapp.database

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class StepsViewModel(private val repository: StepsRepository) : ViewModel() {

    val allWords: LiveData<List<Steps>> = repository.allSteps.asLiveData()

    fun insert(word: Steps) = viewModelScope.launch { // Launching a new coroutine to insert the data in a non-blocking way
        repository.insert(word)
    }
}

class StepsViewModelFactory(private val repository: StepsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StepsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StepsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
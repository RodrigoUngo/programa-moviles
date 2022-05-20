package com.rungo.dummydictionary.ui.word

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rungo.dummydictionary.data.model.Word
import com.rungo.dummydictionary.repository.DictionaryRepository
import kotlinx.coroutines.launch

class WordViewModel(private val repository: DictionaryRepository): ViewModel() {
    private val _status = MutableLiveData<WordUIState>
    val words = repository.getAllWords()

    fun addWord(word: Word) {
        viewModelScope.launch {
            repository.addWord(word)
        }
    }
}
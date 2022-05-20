package com.rungo.dummydictionary

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.rungo.dummydictionary.data.DummyDictionaryDatabase
import com.rungo.dummydictionary.network.RetrofitInstance
import com.rungo.dummydictionary.repository.DictionaryRepository

class DummyDictionaryApplication : Application() {
    private val prefs: SharedPreferences by lazy{
        getSharedPreferences("DummyDictionary", Context.MODE_PRIVATE)
    }

    val dataBase by lazy {
        DummyDictionaryDatabase.getInstance(this)
    }
    fun getAPIService() = with(RetrofitInstance){
        setToken(getToken())
        getWordServices()
    }
    fun getDictionaryRepository() = with(dataBase) {
        DictionaryRepository(wordDao(), synonymDao(), antonymDao())
    }
}
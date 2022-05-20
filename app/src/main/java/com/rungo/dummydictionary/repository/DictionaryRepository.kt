package com.rungo.dummydictionary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rungo.dummydictionary.data.DummyDictionaryDatabase
import com.rungo.dummydictionary.data.dao.AntonymDao
import com.rungo.dummydictionary.data.dao.SynonymDao
import com.rungo.dummydictionary.data.dao.WordDao
import com.rungo.dummydictionary.data.model.Word
import com.rungo.dummydictionary.network.ApiResponse
import com.rungo.dummydictionary.network.WordService
import retrofit2.HttpException
import java.io.IOException

class DictionaryRepository(
    database: DummyDictionaryDatabase,
    private val api: WordService,
    val synonymDao: SynonymDao,
    val antonymDao: AntonymDao
) {
    private val wordDoa = database.wordDao()

    suspend fun getAllWords(): ApiResponse<LiveData<List<Word>>>{
        return try{
            val response = api.getAllWords()
            if(response.count > 0){
                wordDoa.insertWord(response.words)
            }
            ApiResponse.Success(data = wordDoa.getAllWords())
        }catch (e: HttpException){
            ApiResponse.Error(exception = e)
        }catch (e: IOException){
            ApiResponse.Error(exception = e)
        }
    }

    suspend fun addWord(word: Word) {
        wordDoa.insertWord(word)
    }
}
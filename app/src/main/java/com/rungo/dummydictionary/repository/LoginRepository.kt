package com.rungo.dummydictionary.repository

import com.rungo.dummydictionary.network.ApiResponse
import com.rungo.dummydictionary.network.WordService
import com.rungo.dummydictionary.network.dto.LoginRequest
import okio.IOException
import retrofit2.HttpException

class LoginRepository(private val api: WordService) {
    suspend fun login(username:String, password: String): ApiResponse<String>{
        try{
            val response = api.login(LoginRequest(username, password))
            return ApiResponse.Success(response.token)
        } catch (e: HttpException){
            if(e.code() == 400)
                return ApiResponse.ErrorWithMessage(e.response()?.body().toString())
            return ApiResponse.Error(e)
        } catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }
}
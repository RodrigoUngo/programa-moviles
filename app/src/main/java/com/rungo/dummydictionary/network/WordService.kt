package com.rungo.dummydictionary.network;

import com.rungo.dummydictionary.network.dto.LoginRequest
import com.rungo.dummydictionary.network.dto.LoginResponse
import com.rungo.dummydictionary.network.dto.WordsResponse
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

interface WordService {
    @GET("/words")
    suspend fun getAllWords(): WordsResponse

    @POST("/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse
}
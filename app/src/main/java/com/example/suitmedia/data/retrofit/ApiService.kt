package com.example.suitmedia.data.retrofit

import com.example.suitmedia.data.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiResponse
}
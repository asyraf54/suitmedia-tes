package com.example.suitmedia.di


import com.example.suitmedia.data.retrofit.ApiConfig
import com.example.suitmedia.data.retrofit.ApiService

object Injection {
    fun provideApiService(): ApiService {
        return ApiConfig.getApiService()
    }
}
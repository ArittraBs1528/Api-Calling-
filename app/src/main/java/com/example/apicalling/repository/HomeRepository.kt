package com.example.apicalling.repository

import com.example.apicalling.network.Api.HomeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val api : HomeApi) {

    suspend fun getCategoryWiseProducts()= withContext(Dispatchers.IO){
        return@withContext api.getCategories()
    }
}
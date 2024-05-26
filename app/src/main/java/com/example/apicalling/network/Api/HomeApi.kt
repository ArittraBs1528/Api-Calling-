package com.example.apicalling.network.Api

import com.example.apicalling.model.catergory.categorywiseproducts
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET("home/homepagecategorieswithproducts")
    suspend fun getCategories(): Response<categorywiseproducts>

}
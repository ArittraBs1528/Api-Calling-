package com.example.apicalling.screen.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.apicalling.model.catergory.categorywiseproducts
import com.example.apicalling.network.Api.ApiClient
import com.example.apicalling.network.Api.HomeApi
import com.example.apicalling.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _categoryWiseData: MutableLiveData<categorywiseproducts> by lazy {
        MutableLiveData<categorywiseproducts>()
    }


    val categoryWiseData: LiveData<categorywiseproducts>
        get() = _categoryWiseData

    //retrofit setup
    private val apiClient = ApiClient.getRetrofit().create(HomeApi::class.java)


    //setup  homerepository for final call from the api
    private val homeRepository = HomeRepository(apiClient)

    fun getCategoryWiseData() = viewModelScope.launch {
        val response = homeRepository.getCategoryWiseProducts()   // calling for data

        _categoryWiseData.value = response.body()

        println("Our data" + response.body())


    }

}

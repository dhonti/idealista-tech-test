package com.dhontiveros.idealistatechtest.data.remote.api

import com.dhontiveros.idealistatechtest.data.remote.model.PropertyListItemDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("list.json")
    suspend fun getPropertiesList(): Response<List<PropertyListItemDto>>

    @GET("detail.json")
    suspend fun getDetailProperty(): Response<PropertyListItemDto>

}
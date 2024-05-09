package com.example.products_dhruv.api

import com.example.products_dhruv.models.Product
import com.example.products_dhruv.models.ProductsResponseObject
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): ProductsResponseObject

    @GET("products/{id}")
    suspend fun getProductsByID(@Path("id") id: Int): Product
}
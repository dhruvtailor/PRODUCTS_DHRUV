package com.example.products_dhruv.models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val brand: String,
    val thumbnail: String
)

@Serializable
data class ProductsResponseObject(
    val products:List<Product>
)
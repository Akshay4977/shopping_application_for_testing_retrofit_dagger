package com.example.shoppingapplicationfortestingretrofitdagger.repositories

import androidx.lifecycle.LiveData

import com.example.shoppingapplicationfortestingretrofitdagger.data.local.ShoppingItem
import com.example.shoppingapplicationfortestingretrofitdagger.other.Resource
import com.example.shoppingapplicationfortestingretrofitdagger.data.remote.responses.ImageResponse


interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}
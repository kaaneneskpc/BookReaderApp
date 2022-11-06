package com.example.bookreaderapp.repository

import com.example.bookreaderapp.data.Resource
import com.example.bookreaderapp.model.Item
import com.example.bookreaderapp.network.BookApi
import javax.inject.Inject

class BookRepository @Inject constructor(private val bookApi: BookApi) {


    suspend fun getBooks(searchQuery: String): Resource<List<Item>>{

        return try {
            Resource.Loading(data = true)
            val itemList = bookApi.getAllBooks(searchQuery).items
            if (itemList.isNotEmpty()) Resource.Loading(data = false)
            Resource.Success(data = itemList)

        }catch (exception: Exception) {
            Resource.Error(message = exception.message.toString())
        }

    }

    suspend fun getBookInfo(bookId: String): Resource<Item> {

        val response = try {
            Resource.Loading(data = true)
            bookApi.getBookInfo(bookId)
        }catch (exception: Exception){
            return Resource.Error(message = "An error occurred ${exception.message.toString()}")
        }

        Resource.Loading(data = false)
        return Resource.Success(data = response)

    }
}
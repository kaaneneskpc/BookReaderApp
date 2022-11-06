package com.example.bookreaderapp.network

import com.example.bookreaderapp.model.Books
import com.example.bookreaderapp.model.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface BookApi {

    @GET("volumes")
    suspend fun getAllBooks(@Query("q") query: String): Books

    @GET("volumes/{bookId}")
    suspend fun getBookInfo(@Path("bookId") bookId: String): Item

}
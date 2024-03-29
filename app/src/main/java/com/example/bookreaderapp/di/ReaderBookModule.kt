package com.example.bookreaderapp.di

import com.example.bookreaderapp.network.BookApi
import com.example.bookreaderapp.repository.BookRepository
import com.example.bookreaderapp.repository.FireRepository
import com.example.bookreaderapp.utils.Constants
import com.example.bookreaderapp.utils.Constants.BASE_URL
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReaderBookModule {

    @Singleton
    @Provides
    fun provideFireBookRepository()
            = FireRepository(queryBook = FirebaseFirestore.getInstance()
        .collection("books"))


    @Singleton
    @Provides
    fun provideBookApi(): BookApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApi::class.java)
    }

}
package com.example.bookreaderapp.repository

import com.example.bookreaderapp.data.DataOrException
import com.example.bookreaderapp.model.Book
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireRepository @Inject constructor(
    private val queryBook: Query
) {
    suspend fun getAllBooksFromDatabase(): DataOrException<List<Book>, Boolean, Exception> {
        val dataOrException = DataOrException<List<Book>, Boolean, Exception>()

        try {
            dataOrException.apply {
                loading = true
                data =  queryBook.get().await().documents.map { documentSnapshot ->
                    documentSnapshot.toObject(Book::class.java)!!
                }
            }

            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false


        }catch (exception: FirebaseFirestoreException){
            dataOrException.e = exception
        }
        return dataOrException

    }
}
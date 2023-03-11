package com.example.bookreaderapp.ui.screen.detailScreen

import androidx.lifecycle.ViewModel
import com.example.bookreaderapp.data.Resource
import com.example.bookreaderapp.model.Item
import com.example.bookreaderapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReaderDetailScreenViewModel @Inject constructor(private val repository: BookRepository) : ViewModel(){
    suspend fun getBookInfo(bookId: String): Resource<Item> {
        return repository.getBookInfo(bookId)
    }
}
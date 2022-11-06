package com.example.bookreaderapp.ui.screen.searchScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreaderapp.data.Resource
import com.example.bookreaderapp.model.Item
import com.example.bookreaderapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReaderBookSearchScreenViewModel @Inject constructor(private val repository: BookRepository): ViewModel() {
    var listOfBooks: List<Item> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)

    init {
        loadBooks()
    }

    private fun loadBooks() {
        searchBooks("android")
    }

    fun searchBooks(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                return@launch
            }
            runCatching {
                when(val response = repository.getBooks(query)) {
                    is Resource.Success -> {
                        listOfBooks = response.data!!
                        if (listOfBooks.isNotEmpty()) isLoading = false else ""
                    }
                    is Resource.Error -> {
                        isLoading = false
                        Log.e("Network", "searchBooks: Failed getting books", )
                    }
                    else -> { isLoading = false }
                }

            }.onFailure{
                isLoading = false
                Log.d("Network", "searchBooks: ${it.message.toString()}")
            }

        }

    }

}


package com.example.bookreaderapp.model

data class Books(
     var items: List<Item>,
     var kind: String,
     var totalItems: Int
)
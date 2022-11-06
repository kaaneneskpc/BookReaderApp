package com.example.bookreaderapp.model

data class SaleInfo(
     var buyLink: String,
     var country: String,
     var isEbook: Boolean,
     var listPrice: ListPrice,
     var offers: List<Offer>,
     var retailPrice: RetailPrice,
     var saleability: String
)

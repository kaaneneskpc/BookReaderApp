package com.example.bookreaderapp.model

data class Item(var accessInfo: AccessInfo,
                var etag: String,
                var id: String,
                var kind: String,
                var saleInfo: SaleInfo,
                var searchInfo: SearchInfo,
                var selfLink: String,
                var volumeInfo: VolumeInfo)

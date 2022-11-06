package com.example.bookreaderapp.model

data class AccessInfo(var accessViewStatus: String,
                      var country: String,
                      var embeddable: Boolean,
                      var epub: Epub,
                      var pdf: Pdf,
                      var publicDomain: Boolean,
                      var quoteSharingAllowed: Boolean,
                      var textToSpeechPermission: String,
                      var viewability: String,
                      var webReaderLink: String)

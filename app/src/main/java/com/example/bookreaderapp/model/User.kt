package com.example.bookreaderapp.model

data class User(var id: String?,
                var userId: String,
                var displayName: String,
                var avatarUrl: String,
                var quote: String,
                var profession: String){
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf("user_id" to this.userId,
            "display_name" to this.displayName,
            "quote" to this.quote,
            "profession" to this.profession,
            "avatar_url" to this.avatarUrl)
    }

}

package com.example.bookshare.domain

import com.example.bookshare.domain.entity.User


interface UserRepository {
    // We can use email as primary key because Firebase allows only unique emails.
    suspend fun getUserByEmail(email: String, setUser: (user: User?) -> Unit)
}
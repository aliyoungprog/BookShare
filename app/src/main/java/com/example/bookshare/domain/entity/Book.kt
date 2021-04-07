package com.example.bookshare.domain.entity

import java.util.*

data class Book (
    val id: UUID? = null,
    val name: String? = null,
    val author: String? = null,
    val book_img: String? = null,
    val description: String? = null,
    val genre: String? = null,
    val sender: String? = null
    )

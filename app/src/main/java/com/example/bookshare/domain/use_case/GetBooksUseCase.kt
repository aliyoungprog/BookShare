package com.example.bookshare.domain.use_case

import com.example.bookshare.domain.entity.Book

interface GetBooksUseCase {
    fun getAllBooks(): List<Book>
}
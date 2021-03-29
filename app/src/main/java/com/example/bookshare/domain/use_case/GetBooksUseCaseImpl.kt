package com.example.bookshare.domain.use_case

import com.example.bookshare.data.repository.BookRepositoryImpl
import com.example.bookshare.domain.BookRepository
import com.example.bookshare.domain.entity.Book

class GetBooksUseCaseImpl(private val booksRepository: BookRepositoryImpl): GetBooksUseCase {
    override fun getAllBooks(): List<Book> = listOf()
}
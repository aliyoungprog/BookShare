package com.aliyoungprog.domain.use_case

import com.aliyoungprog.data.repository.BookRepositoryImpl
import com.aliyoungprog.domain.entity.Book

class GetBooksUseCaseImpl(private val booksRepository: BookRepositoryImpl): GetBooksUseCase {
    override fun getAllBooks(): List<Book> = listOf()
}

package com.aliyoungprog.domain.use_case

import com.aliyoungprog.domain.entity.Book

interface GetBooksUseCase {
    fun getAllBooks(): List<Book>
}

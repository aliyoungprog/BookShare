package com.example.bookshare.presentation.adapters

import com.example.bookshare.domain.entity.Book

interface ItemClickListener {
    fun onItemClicked(book: Book)
}
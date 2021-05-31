package com.aliyoungprog.presentation.adapters

import com.aliyoungprog.domain.entity.Book

interface ItemClickListener {
    fun onItemClicked(book: Book)
}

package com.example.bookshare.domain.di

import com.example.bookshare.data.repository.BookRepositoryImpl
import com.example.bookshare.data.repository.UserRepositoryImpl
import com.example.bookshare.domain.BookRepository
import com.example.bookshare.domain.UserRepository
import com.example.bookshare.presentation.vm.BooksViewModel
import com.example.bookshare.presentation.vm.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModules = module {
    single <BookRepository> { BookRepositoryImpl() }
    single <UserRepository> { UserRepositoryImpl() }
    viewModel {
        BooksViewModel(get())
    }
    viewModel {
        UserViewModel(get())
    }
}
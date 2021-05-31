package com.aliyoungprog.domain.di

import com.aliyoungprog.data.repository.BookRepositoryImpl
import com.aliyoungprog.data.repository.UserRepositoryImpl
import com.aliyoungprog.domain.BookRepository
import com.aliyoungprog.domain.UserRepository
import com.aliyoungprog.presentation.vm.BooksViewModel
import com.aliyoungprog.presentation.vm.UserViewModel
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

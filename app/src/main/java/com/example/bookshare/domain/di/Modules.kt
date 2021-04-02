package com.example.bookshare.domain.di

import com.example.bookshare.data.repository.BookRepositoryImpl
import com.example.bookshare.presentation.vm.AllBooksViewModel
import get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.dsl.module

val koinModules = module {
    single {BookRepositoryImpl()}
    viewModel {
        AllBooksViewModel(get())
    }
}
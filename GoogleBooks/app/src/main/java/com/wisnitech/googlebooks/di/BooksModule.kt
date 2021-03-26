package com.wisnitech.googlebooks.di

import com.wisnitech.googlebooks.auth.Auth
import com.wisnitech.googlebooks.auth.AuthManager
import com.wisnitech.googlebooks.ui.books.ListBooksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val booksModule = module {

    viewModel { ListBooksViewModel() }

    single {
        val manager: AuthManager = get()
        manager as Auth
    }

    single { AuthManager(context = get()) }

}
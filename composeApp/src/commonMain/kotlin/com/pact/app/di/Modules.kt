package com.pact.di

import com.pact.app.auth.data.AuthRemoteDataSource
import com.pact.app.auth.data.AuthRepositoryImpl
import com.pact.app.auth.domain.AuthRepository
import com.pact.app.auth.presentation.AuthViewModel
import com.pact.app.calendar.presentation.CalendarViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    singleOf(::AuthRemoteDataSource)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    viewModelOf(::AuthViewModel)


    viewModelOf(::CalendarViewModel)
}
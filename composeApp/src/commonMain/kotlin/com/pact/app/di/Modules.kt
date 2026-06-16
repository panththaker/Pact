package com.pact.di

import com.pact.app.auth.data.AuthRemoteDataSource
import com.pact.app.auth.data.AuthRepositoryImpl
import com.pact.app.auth.domain.AuthRepository
import com.pact.app.auth.presentation.AuthViewModel
import com.pact.app.calendar.presentation.CalendarViewModel
import com.pact.app.calendar.presentation.event.EventViewModel
import com.pact.app.core.data.supabase
import com.pact.app.core.domain.SessionManager
import io.github.jan.supabase.SupabaseClient
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    singleOf(::SessionManager)
    singleOf(::AuthRemoteDataSource)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    single{ supabase }
    viewModelOf(::AuthViewModel)


    viewModelOf(::CalendarViewModel)
    viewModelOf(::EventViewModel)
}
package com.pact.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin() {
    startKoin {
        modules(sharedModule)
    }
}
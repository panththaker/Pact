package com.pact.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
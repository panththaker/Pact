package com.pact.app.core.data

import java.util.Properties

actual object SupabaseConfig {
    private val props = Properties().apply {
        load(Thread.currentThread().contextClassLoader?.getResourceAsStream("secret.properties"))
    }
    actual val url: String = props.getProperty("SUPABASE_URL") ?: error("SUPABASE_URL not found")
    actual val key: String = props.getProperty("SUPABASE_ANON_KEY") ?: error("SUPABASE_ANON_KEY not found")
}
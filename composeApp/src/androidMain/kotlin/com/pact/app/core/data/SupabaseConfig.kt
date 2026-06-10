package com.pact.app.core.data
import com.pact.app.BuildConfig

actual object SupabaseConfig {
    actual val url: String = BuildConfig.SUPABASE_URL
    actual val key: String = BuildConfig.SUPABASE_ANON_KEY
}

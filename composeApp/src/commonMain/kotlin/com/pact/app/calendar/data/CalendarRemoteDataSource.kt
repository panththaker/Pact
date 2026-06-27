package com.pact.app.calendar.data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from

class CalendarRemoteDataSource(private val supabaseClient: SupabaseClient) {

    suspend fun getAllEvents(): List<EventDto>{
        return supabaseClient.from("events")
            .select()
            .decodeList<EventDto>()
    }

    suspend fun getEvent(id: String): EventDto {
        return supabaseClient.from("events")
            .select {
                filter {
                    eq("id", id)
                }
            }
            .decodeSingle<EventDto>()
    }

    suspend fun createEvent(event: EventDto): EventDto {
        return supabaseClient.from("events")
            .insert(event) {
                select()
            }
            .decodeSingle<EventDto>()
    }

    suspend fun updateEvent(event: EventDto): EventDto {
        return supabaseClient.from("events")
            .update(event) {
                filter {
                    eq("id", event.id)
                }
                select()
            }
            .decodeSingle<EventDto>()
    }

    suspend fun deleteEvent(id: String) {
        supabaseClient.from("events")
            .delete {
                filter {
                    eq("id", id)
                }
            }
    }
}
package com.pact.app.calendar.domain

import com.pact.app.core.domain.PactEvent
import kotlinx.datetime.Month


interface CalendarRepository {
    suspend fun getAllEvents(): Result<List<PactEvent>>
    suspend fun getEvent(id: String): Result<PactEvent>
    suspend fun createEvent(event: PactEvent): Result<PactEvent>
    suspend fun updateEvent(event: PactEvent): Result<PactEvent>
    suspend fun deleteEvent(id: String): Result<Unit>
}
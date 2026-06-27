package com.pact.app.calendar.data

import com.pact.app.calendar.domain.CalendarRepository
import com.pact.app.core.domain.PactEvent

class CalendarRepositoryImpl(
    private val calendarRemoteDataSource: CalendarRemoteDataSource
) : CalendarRepository {

    override suspend fun getAllEvents(): Result<List<PactEvent>> {
        return try {
            val events = calendarRemoteDataSource.getAllEvents().map { it.toDomain() }
            Result.success(events)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getEvent(id: String): Result<PactEvent> {
        return try {
            val event = calendarRemoteDataSource.getEvent(id).toDomain()
            Result.success(event)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createEvent(event: PactEvent): Result<PactEvent> {
        return try {
            val created = calendarRemoteDataSource.createEvent(event.toDto()).toDomain()
            Result.success(created)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateEvent(event: PactEvent): Result<PactEvent> {
        return try {
            val updated = calendarRemoteDataSource.updateEvent(event.toDto()).toDomain()
            Result.success(updated)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteEvent(id: String): Result<Unit> {
        return try {
            calendarRemoteDataSource.deleteEvent(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
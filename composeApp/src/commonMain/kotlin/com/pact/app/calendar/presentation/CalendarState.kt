package com.pact.app.calendar.presentation

import com.pact.app.core.domain.PactEvent
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

@OptIn(ExperimentalTime::class)
data class CalendarState(
    val selectedView: CalendarViewType = CalendarViewType.MONTH,
    val selectedDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    val todayDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    val todayDateTime: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
    val events: List<PactEvent> = emptyList(),
    val errorMessage: String? = null
)

//listOf(
//        PactEvent(
//            id = "1",
//            title = "Deep focus: calendar mocks",
//            date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
//            startTime = 540,
//            endTime = 660,
//            color = 0xFF7C5CD9L,
//            reminder = ReminderTime(ReminderType.TEN_MIN),
//            repeat = RepeatType.NONE,
//            notes = null,
//            isCompleted = false
//        ),
//        PactEvent(
//            id = "2",
//            title = "Design review w/ Mia",
//            date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
//            startTime = 660,
//            endTime = 720,
//            color = 0xFFF5A623L,
//            reminder = ReminderTime(ReminderType.NONE),
//            repeat = RepeatType.NONE,
//            notes = null,
//            isCompleted = false
//        ),
//        PactEvent(
//            id = "3",
//            title = "Lunch + walk",
//            date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
//            startTime = 750,
//            endTime = 810,
//            color = 0xFF4CAF50L,
//            reminder = ReminderTime(ReminderType.NONE),
//            repeat = RepeatType.NONE,
//            notes = null,
//            isCompleted = false
//        ),
//        PactEvent(
//            id = "4",
//            title = "Inbox + admin",
//            date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
//            startTime = 840,
//            endTime = 900,
//            color = 0xFF7C5CD9L,
//            reminder = ReminderTime(ReminderType.NONE),
//            repeat = RepeatType.NONE,
//            notes = null,
//            isCompleted = false
//        ),
//        PactEvent(
//            id = "5",
//            title = "Stretch & tea",
//            date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
//            startTime = 960,
//            endTime = 990,
//            color = 0xFFE91E63L,
//            reminder = ReminderTime(ReminderType.NONE),
//            repeat = RepeatType.NONE,
//            notes = null,
//            isCompleted = false
//        ),
//        PactEvent(
//            id = "6",
//            title = "Call with James",
//            date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
//            startTime = 1020,
//            endTime = 1080,
//            color = 0xFF4CAF50L,
//            reminder = ReminderTime(ReminderType.NONE),
//            repeat = RepeatType.NONE,
//            notes = null,
//            isCompleted = false
//        ),
//        PactEvent(
//            id = "7",
//            title = "Dinner with Sam",
//            date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
//            startTime = 1140,
//            endTime = 1200,
//            color = 0xFFF5A623L,
//            reminder = ReminderTime(ReminderType.NONE),
//            repeat = RepeatType.NONE,
//            notes = null,
//            isCompleted = false
//        ),
//        PactEvent(
//            id = "8",
//            title = "Evening walk",
//            date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
//            startTime = 1200,
//            endTime = 1230,
//            color = 0xFF7C5CD9L,
//            reminder = ReminderTime(ReminderType.NONE),
//            repeat = RepeatType.NONE,
//            notes = null,
//            isCompleted = false
//        )
//    )
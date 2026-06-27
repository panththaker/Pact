package com.pact.app.calendar.data

import com.pact.app.core.domain.PactEvent
import com.pact.app.core.domain.ReminderTime
import com.pact.app.core.domain.ReminderType
import com.pact.app.core.domain.RepeatType
import kotlinx.datetime.LocalDate

fun EventDto.toDomain(): PactEvent {
    return PactEvent(
        id = id,
        title = title,
        date = LocalDate.parse(date),
        startTime = startTime,
        endTime = endTime,
        color = color,
        reminder = ReminderTime(
            type = ReminderType.valueOf(reminderType),
            customMinutes = reminderCustomMinutes
        ),
        repeat = RepeatType.valueOf(repeatType),
        notes = notes,
        isCompleted = isCompleted
    )
}

fun PactEvent.toDto(): EventDto {
    return EventDto(
        id = id,
        title = title,
        date = date.toString(),
        startTime = startTime,
        endTime = endTime,
        color = color,
        reminderType = reminder.type.name,
        reminderCustomMinutes = reminder.customMinutes,
        repeatType = repeat.name,
        notes = notes,
        isCompleted = isCompleted
    )
}
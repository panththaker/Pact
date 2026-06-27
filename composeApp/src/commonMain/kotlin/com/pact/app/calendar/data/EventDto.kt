package com.pact.app.calendar.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventDto(
    val id: String,

    @SerialName("user_id")
    val userId: String? = null,

    val title: String,

    val date: String,

    @SerialName("start_time")
    val startTime: Int,

    @SerialName("end_time")
    val endTime: Int,

    val color: Long,

    @SerialName("reminder_type")
    val reminderType: String,

    @SerialName("reminder_custom_minutes")
    val reminderCustomMinutes: Int? = null,

    @SerialName("repeat_type")
    val repeatType: String,

    val notes: String? = null,

    @SerialName("is_completed")
    val isCompleted: Boolean = false,

    @SerialName("created_at")
    val createdAt: String? = null    // server-generated, not sent on insert

)
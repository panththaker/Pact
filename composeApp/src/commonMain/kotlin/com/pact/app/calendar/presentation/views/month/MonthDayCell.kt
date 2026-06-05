package com.pact.app.calendar.presentation.views.month

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pact.app.Primary
import com.pact.app.PrimaryTint
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import com.pact.app.Text1


@Composable
fun MonthDayCell(
    day: Int?,
    isToday: Boolean,
    isSelected: Boolean,
    hasEvents: Boolean,
    onClick: () -> Unit
){

    val backgroundColor = when {
        isSelected -> Primary
        isToday -> PrimaryTint
        else -> Color.Transparent
    }

    val textColor = when {
        isSelected -> Color.White
        isToday -> Primary
        else -> Text1
    }

    val dotColor = when {
        isSelected -> Color.White
        else -> Color.Gray
    }


    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .clickable(enabled = day != null){ onClick() },
        contentAlignment = Alignment.Center,
    ){
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            if(day != null){
                Text(
                    text = day.toString(),
                    color = textColor
                )
                Spacer(Modifier.height(2.dp))
                if (hasEvents) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(
                                color = dotColor,
                                shape = CircleShape
                            ),
                    )
                }
            }

        }

    }
}
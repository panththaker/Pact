package com.pact.app.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pact.app.icons.calendar_today
import com.pact.app.icons.chat_bubble
import com.pact.app.icons.person
import com.pact.app.icons.todo
import com.pact.app.Primary
import com.pact.app.Surface
import com.pact.app.SurfaceWash
import com.pact.app.Text3

enum class NavTab {
    CALENDAR, TODO, CHAT, PROFILE
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun PactNavBar(
    selectedTab: NavTab,
    onTabSelected: (NavTab) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Surface)
            .navigationBarsPadding()
            .padding(horizontal = 24.dp, vertical = 12.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NavTab.entries.forEach { tab ->
            val isSelected = tab == selectedTab
            val tint = if (isSelected) Primary else Text3

            TextButton(
                onClick = {onTabSelected(tab)},
                colors = ButtonDefaults.textButtonColors(
                    containerColor = if (isSelected) SurfaceWash else Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(85.dp)
                    .height(64.dp)
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        imageVector = when (tab) {
                            NavTab.CALENDAR -> calendar_today
                            NavTab.TODO -> todo
                            NavTab.CHAT -> chat_bubble
                            NavTab.PROFILE -> person
                        },
                        contentDescription = tab.name,
                        tint = tint
                    )
                    Text(
                        text = tab.name.lowercase().replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.labelSmall,
                        color = tint,

                    )
                }
            }
        }
    }
}
package com.prepmaster.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.prepmaster.app.ui.theme.*

private data class NavItem(val route: String, val label: String, val icon: ImageVector)
private val items = listOf(
    NavItem("home",      "হোম",      Icons.Filled.Home),
    NavItem("learn",     "শেখো",     Icons.Filled.MenuBook),
    NavItem("practice",  "Practice", Icons.Filled.Edit),
    NavItem("quiz",      "Quiz",     Icons.Filled.Quiz),
    NavItem("flashcard", "Card",     Icons.Filled.Style),
    NavItem("reference", "Reference",Icons.Filled.LibraryBooks),
)

@Composable
fun PrepBottomBar(currentRoute: String, onItemClick: (String) -> Unit) {
    Surface(color = BgSurface, shadowElevation = 12.dp,
        modifier = Modifier.fillMaxWidth()) {
        Row(
            Modifier.fillMaxWidth().height(62.dp).padding(horizontal = 2.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val selected = currentRoute == item.route
                Column(
                    Modifier.weight(1f).fillMaxHeight()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onItemClick(item.route) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        Modifier.size(if (selected) 38.dp else 30.dp)
                            .background(if (selected) Purple.copy(0.2f) else Color.Transparent,
                                RoundedCornerShape(9.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(item.icon, null, tint = if (selected) Purple else TxtHint,
                            modifier = Modifier.size(20.dp))
                    }
                    Spacer(Modifier.height(2.dp))
                    Text(item.label, style = MaterialTheme.typography.labelSmall,
                        color = if (selected) Purple else TxtHint)
                    if (selected) {
                        Spacer(Modifier.height(2.dp))
                        Box(Modifier.size(4.dp).background(Purple, CircleShape))
                    } else Spacer(Modifier.height(6.dp))
                }
            }
        }
    }
}

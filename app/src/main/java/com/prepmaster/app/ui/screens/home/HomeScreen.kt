package com.prepmaster.app.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.prepmaster.app.ui.AppViewModel
import com.prepmaster.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(nav: NavController, vm: AppViewModel = viewModel()) {
    val stats    by vm.stats.collectAsStateWithLifecycle()
    val progress by vm.progress.collectAsStateWithLifecycle()
    val cats      = vm.categories

    val learnedIds = progress.filter { it.learned }.map { it.prepId }.toSet()
    val totalPreps = cats.sumOf { it.prepositions.size }
    val overallProg= if (totalPreps>0) learnedIds.size.toFloat()/totalPreps else 0f

    Column(
        Modifier.fillMaxSize().background(BgDeep).verticalScroll(rememberScrollState())
    ) {
        // ── Hero Header ───────────────────────────────────────────────
        Box(
            Modifier.fillMaxWidth().background(
                Brush.linearGradient(listOf(Color(0xFF1A0A3A),Color(0xFF0A1A3A),BgDeep),
                    start = Offset.Zero, end = Offset(Float.MAX_VALUE,400f))
            ).padding(20.dp, 52.dp, 20.dp, 28.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("স্বাগতম,", style=MaterialTheme.typography.bodyMedium, color=TxtSecondary)
                        Text(stats.name, style=MaterialTheme.typography.headlineMedium, fontWeight=FontWeight.ExtraBold)
                        Text("Preposition Master", style=MaterialTheme.typography.bodySmall,
                            color=Purple.copy(0.8f), fontWeight=FontWeight.Medium)
                    }
                    Box(Modifier.size(56.dp).background(
                        Brush.radialGradient(listOf(Purple,PurpleDark)), CircleShape),
                        contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Lv", style=MaterialTheme.typography.labelSmall, color=Color.White.copy(0.7f))
                            Text("${stats.level}", style=MaterialTheme.typography.headlineSmall,
                                color=Color.White, fontWeight=FontWeight.ExtraBold)
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))

                val (xpEarned, xpNeeded) = vm.xpProgress(stats.xp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("${stats.xp} XP", style=MaterialTheme.typography.labelLarge, color=Gold)
                    Spacer(Modifier.weight(1f))
                    Text("Level ${stats.level+1} এ $xpNeeded XP দরকার",
                        style=MaterialTheme.typography.labelSmall, color=TxtHint)
                }
                Spacer(Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = { if(xpNeeded>0)(xpEarned.toFloat()/xpNeeded).coerceIn(0f,1f) else 0f },
                    modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                    color = Gold, trackColor = BgElevated
                )
            }
        }

        // ── Stats Row ─────────────────────────────────────────────────
        Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement=Arrangement.spacedBy(10.dp)) {
            StatCard("${stats.streak}", "Streak", Icons.Filled.Whatshot, Coral, Modifier.weight(1f))
            StatCard("${learnedIds.size}", "শেখা হয়েছে", Icons.Filled.School, Mint, Modifier.weight(1f))
            StatCard("${stats.quizCorrect}", "Quiz সঠিক", Icons.Filled.Star, Gold, Modifier.weight(1f))
        }

        // ── Daily Goal ────────────────────────────────────────────────
        Card(Modifier.fillMaxWidth().padding(horizontal=16.dp),
            colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(16.dp)) {
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment=Alignment.CenterVertically) {
                    Icon(Icons.Filled.Timer, null, tint=Cyan, modifier=Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("আজকের লক্ষ্য", style=MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.weight(1f))
                    Text("${stats.todayMins}/${stats.studyGoalMins} মিনিট",
                        style=MaterialTheme.typography.bodySmall, color=Cyan)
                }
                Spacer(Modifier.height(10.dp))
                val goalProg = if(stats.studyGoalMins>0)(stats.todayMins.toFloat()/stats.studyGoalMins).coerceIn(0f,1f) else 0f
                LinearProgressIndicator(
                    progress={goalProg},
                    modifier=Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                    color=Cyan, trackColor=BgElevated
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // ── Overall Progress ──────────────────────────────────────────
        Card(Modifier.fillMaxWidth().padding(horizontal=16.dp),
            colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(16.dp)) {
            Row(Modifier.padding(16.dp), verticalAlignment=Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("মোট অগ্রগতি", style=MaterialTheme.typography.titleMedium)
                    Text("${learnedIds.size} / $totalPreps টি Preposition শেখা হয়েছে",
                        style=MaterialTheme.typography.bodySmall)
                    Spacer(Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress={overallProg},
                        modifier=Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                        color=Mint, trackColor=BgElevated
                    )
                }
                Spacer(Modifier.width(16.dp))
                Box(Modifier.size(56.dp).background(Mint.copy(0.15f),CircleShape),
                    contentAlignment=Alignment.Center) {
                    Text("${(overallProg*100).toInt()}%",
                        style=MaterialTheme.typography.headlineSmall, color=Mint, fontWeight=FontWeight.ExtraBold)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // ── Quick Actions ─────────────────────────────────────────────
        Text("দ্রুত শুরু করো", style=MaterialTheme.typography.titleLarge,
            modifier=Modifier.padding(horizontal=16.dp))
        Spacer(Modifier.height(10.dp))

        Row(Modifier.fillMaxWidth().padding(horizontal=16.dp), horizontalArrangement=Arrangement.spacedBy(10.dp)) {
            QuickCard("Quiz দাও", Icons.Filled.Quiz, Coral, Modifier.weight(1f)) { nav.navigate("quiz") }
            QuickCard("Practice", Icons.Filled.Edit, Mint, Modifier.weight(1f)) { nav.navigate("practice") }
        }
        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth().padding(horizontal=16.dp), horizontalArrangement=Arrangement.spacedBy(10.dp)) {
            QuickCard("Flashcard", Icons.Filled.Style, Gold, Modifier.weight(1f)) { nav.navigate("flashcard") }
            QuickCard("Stories", Icons.Filled.AutoStories, Cyan, Modifier.weight(1f)) { nav.navigate("stories") }
        }
        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth().padding(horizontal=16.dp), horizontalArrangement=Arrangement.spacedBy(10.dp)) {
            QuickCard("Reference", Icons.Filled.LibraryBooks, Purple, Modifier.weight(1f)) { nav.navigate("reference") }
            QuickCard("প্রোফাইল", Icons.Filled.Person, Lavender, Modifier.weight(1f)) { nav.navigate("profile") }
        }

        Spacer(Modifier.height(16.dp))

        // ── Categories Overview ───────────────────────────────────────
        Text("Preposition Categories", style=MaterialTheme.typography.titleLarge,
            modifier=Modifier.padding(horizontal=16.dp))
        Spacer(Modifier.height(10.dp))

        cats.forEach { cat ->
            val catLearned = learnedIds.count { id -> cat.prepositions.any { it.id == id } }
            val catProg = if(cat.prepositions.isNotEmpty()) catLearned.toFloat()/cat.prepositions.size else 0f
            val catColor = Color(cat.color)

            Card(
                onClick = { nav.navigate("learn") },
                modifier = Modifier.fillMaxWidth().padding(horizontal=16.dp, vertical=4.dp),
                colors = CardDefaults.cardColors(BgCard),
                shape = RoundedCornerShape(14.dp)
            ) {
                Row(Modifier.padding(12.dp), verticalAlignment=Alignment.CenterVertically) {
                    Box(Modifier.size(44.dp).background(catColor.copy(0.18f),RoundedCornerShape(10.dp)),
                        contentAlignment=Alignment.Center) {
                        Icon(Icons.Filled.BookmarkBorder, null, tint=catColor, modifier=Modifier.size(22.dp))
                    }
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Row {
                            Text(cat.titleBn, style=MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.width(6.dp))
                            Text("(${cat.title})", style=MaterialTheme.typography.bodySmall, color=catColor)
                        }
                        Text(cat.description, style=MaterialTheme.typography.bodySmall)
                        Spacer(Modifier.height(5.dp))
                        LinearProgressIndicator(
                            progress={catProg},
                            modifier=Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)),
                            color=catColor, trackColor=BgElevated
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Text("$catLearned/${cat.prepositions.size}",
                        style=MaterialTheme.typography.bodySmall, color=catColor)
                }
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun StatCard(value:String, label:String, icon: ImageVector, color:Color, modifier:Modifier) {
    Card(modifier, colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(14.dp)) {
        Column(Modifier.padding(12.dp), horizontalAlignment=Alignment.CenterHorizontally) {
            Icon(icon, null, tint=color, modifier=Modifier.size(20.dp))
            Spacer(Modifier.height(4.dp))
            Text(value, style=MaterialTheme.typography.headlineSmall, color=color, fontWeight=FontWeight.ExtraBold)
            Text(label, style=MaterialTheme.typography.labelSmall)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickCard(title:String, icon: ImageVector, color:Color, modifier:Modifier, onClick:()->Unit) {
    Card(modifier, colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(14.dp), onClick=onClick) {
        Row(Modifier.padding(14.dp), verticalAlignment=Alignment.CenterVertically) {
            Box(Modifier.size(34.dp).background(color.copy(0.18f),RoundedCornerShape(8.dp)),
                contentAlignment=Alignment.Center) {
                Icon(icon, null, tint=color, modifier=Modifier.size(18.dp))
            }
            Spacer(Modifier.width(8.dp))
            Text(title, style=MaterialTheme.typography.titleMedium)
        }
    }
}

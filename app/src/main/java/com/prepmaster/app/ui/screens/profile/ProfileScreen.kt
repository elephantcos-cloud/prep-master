package com.prepmaster.app.ui.screens.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prepmaster.app.data.db.entity.UserEntity
import com.prepmaster.app.data.repository.AppRepository
import com.prepmaster.app.ui.AppViewModel
import com.prepmaster.app.ui.theme.*
import com.prepmaster.app.worker.WorkScheduler
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class BadgeDef(val id:String, val title:String, val desc:String, val color:Color)

val badgeDefs = listOf(
    BadgeDef("first_prep","প্রথম পদক্ষেপ","প্রথম Preposition শেখো",Mint),
    BadgeDef("explorer","অন্বেষণকারী","১০টি Preposition শেখো",Cyan),
    BadgeDef("learner","শিক্ষার্থী","২৫টি Preposition শেখো",Purple),
    BadgeDef("scholar","পণ্ডিত","৫০টি Preposition শেখো",Gold),
    BadgeDef("xp_100","XP ১০০","১০০ XP অর্জন",Coral),
    BadgeDef("xp_500","XP ৫০০","৫০০ XP অর্জন",Orange),
    BadgeDef("xp_1000","XP ১০০০","১০০০ XP অর্জন",Lavender),
    BadgeDef("streak_3","৩ দিন","৩ দিন ধারাবাহিক",Coral),
    BadgeDef("streak_7","সপ্তাহের যোদ্ধা","৭ দিন ধারাবাহিক",Gold),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onBack: () -> Unit, vm: AppViewModel = viewModel()) {
    val ctx   = LocalContext.current
    val repo  = remember { AppRepository.get(ctx) }
    val scope = rememberCoroutineScope()
    val stats  by vm.stats.collectAsStateWithLifecycle()
    val badges by vm.badges.collectAsStateWithLifecycle()
    val user   by repo.userFlow().collectAsStateWithLifecycle(null)

    var name       by remember { mutableStateOf("") }
    var notifOn    by remember { mutableStateOf(true) }
    var goalMins   by remember { mutableIntStateOf(20) }

    LaunchedEffect(user) {
        user?.let { name=it.name; notifOn=it.notifEnabled; goalMins=it.studyGoalMins }
    }

    val earnedIds = badges.map{it.id}.toSet()
    val (xpEarned, xpNeeded) = vm.xpProgress(stats.xp)

    Column(Modifier.fillMaxSize().background(BgDeep)) {
        Surface(color=BgSurface, shadowElevation=2.dp) {
            Row(Modifier.fillMaxWidth().padding(4.dp,40.dp,16.dp,8.dp), verticalAlignment=Alignment.CenterVertically) {
                IconButton(onBack){ Icon(Icons.Filled.ArrowBack,null,tint=TxtPrimary) }
                Text("প্রোফাইল", style=MaterialTheme.typography.headlineSmall)
            }
        }

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            // Hero
            Box(Modifier.fillMaxWidth().background(
                Brush.verticalGradient(listOf(Color(0xFF1A0A3A),BgDeep)))
                .padding(24.dp,32.dp,24.dp,28.dp)) {
                Column(horizontalAlignment=Alignment.CenterHorizontally, modifier=Modifier.fillMaxWidth()) {
                    Box(Modifier.size(80.dp).background(
                        Brush.radialGradient(listOf(Purple,PurpleDark)), CircleShape),
                        contentAlignment=Alignment.Center) {
                        Text(stats.name.take(1), style=MaterialTheme.typography.headlineLarge,
                            color=Color.White, fontWeight=FontWeight.ExtraBold)
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(stats.name, style=MaterialTheme.typography.headlineMedium)
                    Text("Level ${stats.level} • Preposition Master",
                        style=MaterialTheme.typography.bodyMedium, color=Purple)
                    Spacer(Modifier.height(16.dp))
                    Text("${stats.xp} XP", style=MaterialTheme.typography.titleLarge, color=Gold)
                    Spacer(Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress={if(xpNeeded>0)(xpEarned.toFloat()/xpNeeded).coerceIn(0f,1f) else 0f},
                        modifier=Modifier.fillMaxWidth(0.75f).height(8.dp).clip(RoundedCornerShape(4.dp)),
                        color=Gold, trackColor=BgElevated)
                    Text("$xpEarned/$xpNeeded XP → Level ${stats.level+1}",
                        style=MaterialTheme.typography.labelSmall, color=TxtHint)
                }
            }

            // Stats grid
            Text("পরিসংখ্যান", style=MaterialTheme.typography.titleLarge, modifier=Modifier.padding(16.dp,8.dp,16.dp,8.dp))
            val statItems = listOf(
                Triple("${stats.streak}","দিনের Streak",Coral),
                Triple("${stats.totalStudied}","শেখা হয়েছে",Mint),
                Triple("${if(stats.quizTotal>0) stats.quizCorrect*100/stats.quizTotal else 0}%","Quiz সঠিকতা",Purple),
                Triple("${earnedIds.size}","Badge",Gold),
                Triple("${stats.practiceCorrect}","Practice সঠিক",Cyan),
                Triple("${stats.flashcardsViewed}","Flashcard দেখা",Orange),
            )
            val rows = statItems.chunked(3)
            rows.forEach { row ->
                Row(Modifier.fillMaxWidth().padding(horizontal=16.dp), horizontalArrangement=Arrangement.spacedBy(10.dp)) {
                    row.forEach { (v,l,c) ->
                        Card(Modifier.weight(1f), colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(14.dp)) {
                            Column(Modifier.padding(12.dp), horizontalAlignment=Alignment.CenterHorizontally) {
                                Text(v, style=MaterialTheme.typography.headlineMedium, color=c, fontWeight=FontWeight.ExtraBold)
                                Text(l, style=MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                    repeat(3-row.size){ Spacer(Modifier.weight(1f)) }
                }
                Spacer(Modifier.height(10.dp))
            }

            // Badges
            Text("Badge সংগ্রহ (${earnedIds.size}/${badgeDefs.size})",
                style=MaterialTheme.typography.titleLarge, modifier=Modifier.padding(16.dp,8.dp,16.dp,8.dp))
            LazyRow(contentPadding=PaddingValues(horizontal=16.dp), horizontalArrangement=Arrangement.spacedBy(10.dp)) {
                items(badgeDefs) { b ->
                    val earned = b.id in earnedIds
                    Card(Modifier.width(96.dp),
                        colors=CardDefaults.cardColors(if(earned) b.color.copy(0.1f) else BgCard),
                        border=if(earned) BorderStroke(1.dp,b.color.copy(0.5f)) else null,
                        shape=RoundedCornerShape(12.dp)) {
                        Column(Modifier.padding(10.dp), horizontalAlignment=Alignment.CenterHorizontally) {
                            Box(Modifier.size(38.dp).background(
                                if(earned) b.color.copy(0.2f) else BgElevated, CircleShape),
                                contentAlignment=Alignment.Center) {
                                Text(if(earned) "★" else "○",
                                    color=if(earned) b.color else TxtHint, fontSize=20.sp)
                            }
                            Spacer(Modifier.height(6.dp))
                            Text(b.title, style=MaterialTheme.typography.labelSmall,
                                color=if(earned) b.color else TxtHint,
                                maxLines=2, textAlign=androidx.compose.ui.text.style.TextAlign.Center)
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Settings
            Card(Modifier.fillMaxWidth().padding(horizontal=16.dp),
                colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(18.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text("সেটিংস", style=MaterialTheme.typography.titleLarge, color=Purple)
                    Spacer(Modifier.height(14.dp))
                    OutlinedTextField(value=name, onValueChange={name=it}, label={Text("তোমার নাম")},
                        modifier=Modifier.fillMaxWidth(),
                        colors=OutlinedTextFieldDefaults.colors(focusedBorderColor=Purple,
                            unfocusedBorderColor=Divider, focusedContainerColor=BgCard2, unfocusedContainerColor=BgCard2),
                        shape=RoundedCornerShape(12.dp))
                    Spacer(Modifier.height(14.dp))
                    Row(verticalAlignment=Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text("Notification", style=MaterialTheme.typography.titleMedium)
                            Text("প্রতি ৩ ঘণ্টায় reminder", style=MaterialTheme.typography.bodySmall, color=TxtHint)
                        }
                        Switch(checked=notifOn, onCheckedChange={notifOn=it},
                            colors=SwitchDefaults.colors(checkedThumbColor=Purple,checkedTrackColor=Purple.copy(0.4f)))
                    }
                    Spacer(Modifier.height(14.dp))
                    Text("দৈনিক লক্ষ্য: $goalMins মিনিট", style=MaterialTheme.typography.bodyMedium)
                    Slider(value=goalMins.toFloat(), onValueChange={goalMins=it.toInt()},
                        valueRange=10f..60f, steps=9,
                        colors=SliderDefaults.colors(thumbColor=Purple,activeTrackColor=Purple))
                    Spacer(Modifier.height(16.dp))
                    Button(onClick={
                        scope.launch {
                            val u = repo.userFlow().first() ?: return@launch
                            vm.updateUser(u.copy(name=name.ifBlank{"শিক্ষার্থী"},
                                studyGoalMins=goalMins, notifEnabled=notifOn))
                            if(notifOn) WorkScheduler.schedule(ctx)
                            else WorkScheduler.cancel(ctx)
                            onBack()
                        }
                    }, modifier=Modifier.fillMaxWidth().height(52.dp),
                        colors=ButtonDefaults.buttonColors(Purple), shape=RoundedCornerShape(14.dp)) {
                        Icon(Icons.Filled.Save,null)
                        Spacer(Modifier.width(8.dp))
                        Text("সংরক্ষণ করো", fontWeight=FontWeight.Bold, fontSize=16.sp)
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

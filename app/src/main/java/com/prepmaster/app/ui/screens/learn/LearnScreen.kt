package com.prepmaster.app.ui.screens.learn

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prepmaster.app.ui.AppViewModel
import com.prepmaster.app.ui.components.PrepIllustration
import com.prepmaster.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnScreen(onCategory: (String) -> Unit, vm: AppViewModel = viewModel()) {
    val cats     = vm.categories
    val progress by vm.progress.collectAsStateWithLifecycle()
    val learnedIds = progress.filter{it.learned}.map{it.prepId}.toSet()

    Column(Modifier.fillMaxSize().background(BgDeep)) {
        Surface(color=BgSurface, shadowElevation=2.dp) {
            Column(Modifier.fillMaxWidth().padding(16.dp,48.dp,16.dp,14.dp)) {
                Text("Preposition শেখো", style=MaterialTheme.typography.headlineMedium)
                Text("${cats.sumOf{it.prepositions.size}} টি Preposition — ৯টি Category",
                    style=MaterialTheme.typography.bodySmall, color=TxtSecondary)
            }
        }
        LazyColumn(contentPadding=PaddingValues(16.dp), verticalArrangement=Arrangement.spacedBy(14.dp)) {
            items(cats) { cat ->
                val catLearned = learnedIds.count{id->cat.prepositions.any{it.id==id}}
                val catProg = if(cat.prepositions.isNotEmpty()) catLearned.toFloat()/cat.prepositions.size else 0f
                val catColor = Color(cat.color)

                Card(colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(18.dp),
                    onClick={onCategory(cat.id)},
                    modifier=Modifier.fillMaxWidth()) {
                    Column {
                        // Color bar at top
                        Box(Modifier.fillMaxWidth().height(6.dp).background(
                            catColor.copy(if(catProg>=1f) 1f else 0.5f), RoundedCornerShape(topStart=18.dp,topEnd=18.dp)))

                        Row(Modifier.padding(16.dp), verticalAlignment=Alignment.CenterVertically) {
                            // Illustration preview
                            PrepIllustration(
                                imageType = cat.prepositions.firstOrNull()?.imageType ?: "",
                                color = catColor,
                                modifier = Modifier.size(72.dp)
                            )
                            Spacer(Modifier.width(14.dp))
                            Column(Modifier.weight(1f)) {
                                Row(verticalAlignment=Alignment.CenterVertically) {
                                    Text(cat.titleBn, style=MaterialTheme.typography.headlineSmall)
                                    Spacer(Modifier.width(6.dp))
                                    Surface(shape=RoundedCornerShape(6.dp), color=catColor.copy(0.18f)) {
                                        Text(cat.title, modifier=Modifier.padding(6.dp,2.dp),
                                            style=MaterialTheme.typography.labelSmall, color=catColor)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Text(cat.description, style=MaterialTheme.typography.bodySmall)
                                Spacer(Modifier.height(8.dp))
                                Row(verticalAlignment=Alignment.CenterVertically) {
                                    LinearProgressIndicator(
                                        progress={catProg},
                                        modifier=Modifier.weight(1f).height(6.dp).clip(RoundedCornerShape(3.dp)),
                                        color=catColor, trackColor=BgElevated
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text("$catLearned/${cat.prepositions.size}",
                                        style=MaterialTheme.typography.labelSmall, color=catColor)
                                }
                            }
                            Icon(Icons.Filled.ChevronRight, null, tint=TxtHint)
                        }

                        // Preposition chips preview
                        Row(Modifier.padding(start=16.dp,end=16.dp,bottom=12.dp),
                            horizontalArrangement=Arrangement.spacedBy(6.dp)) {
                            cat.prepositions.take(5).forEach { prep ->
                                val learned = prep.id in learnedIds
                                Surface(shape=RoundedCornerShape(6.dp),
                                    color=if(learned) catColor.copy(0.25f) else BgElevated,
                                    border=if(learned) BorderStroke(1.dp,catColor.copy(0.5f)) else null) {
                                    Text(prep.word, modifier=Modifier.padding(8.dp,4.dp),
                                        style=MaterialTheme.typography.labelLarge,
                                        color=if(learned) catColor else TxtHint,
                                        fontWeight=if(learned) FontWeight.Bold else FontWeight.Normal)
                                }
                            }
                            if(cat.prepositions.size>5) {
                                Surface(shape=RoundedCornerShape(6.dp), color=BgElevated) {
                                    Text("+${cat.prepositions.size-5}", modifier=Modifier.padding(8.dp,4.dp),
                                        style=MaterialTheme.typography.labelSmall, color=TxtHint)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

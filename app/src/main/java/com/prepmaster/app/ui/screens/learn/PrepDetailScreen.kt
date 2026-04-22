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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prepmaster.app.ui.AppViewModel
import com.prepmaster.app.ui.components.PrepIllustration
import com.prepmaster.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrepDetailScreen(catId: String, onBack: () -> Unit, vm: AppViewModel = viewModel()) {
    val cat     = vm.getCategory(catId) ?: return
    val progress by vm.progress.collectAsStateWithLifecycle()
    val learnedIds = progress.filter{it.learned}.map{it.prepId}.toSet()
    val catColor = Color(cat.color)

    var selectedIdx by remember { mutableIntStateOf(0) }
    val prep = cat.prepositions.getOrNull(selectedIdx)

    Column(Modifier.fillMaxSize().background(BgDeep)) {
        // Top bar
        Surface(color=BgSurface, shadowElevation=2.dp) {
            Row(Modifier.fillMaxWidth().padding(4.dp,40.dp,16.dp,8.dp), verticalAlignment=Alignment.CenterVertically) {
                IconButton(onBack){ Icon(Icons.Filled.ArrowBack,null,tint=TxtPrimary) }
                Column(Modifier.weight(1f)) {
                    Text(cat.titleBn, style=MaterialTheme.typography.headlineSmall)
                    Text("${cat.prepositions.size} টি Preposition",
                        style=MaterialTheme.typography.bodySmall, color=catColor)
                }
            }
        }

        // Prep selector row
        LazyRow(contentPadding=PaddingValues(horizontal=14.dp, vertical=10.dp),
            horizontalArrangement=Arrangement.spacedBy(8.dp)) {
            itemsIndexed(cat.prepositions) { idx, p ->
                val isSelected = idx == selectedIdx
                val isLearned  = p.id in learnedIds
                Surface(
                    shape=RoundedCornerShape(10.dp),
                    color=when{isSelected->catColor; isLearned->catColor.copy(0.2f); else->BgCard},
                    border=if(isLearned&&!isSelected) BorderStroke(1.dp,catColor.copy(0.4f)) else null,
                    modifier=Modifier.clickable{ selectedIdx=idx }
                ) {
                    Row(Modifier.padding(10.dp,6.dp), verticalAlignment=Alignment.CenterVertically) {
                        if(isLearned) Icon(Icons.Filled.Check, null, tint=if(isSelected) Color.White else catColor,
                            modifier=Modifier.size(12.dp).then(Modifier.padding(end=4.dp)))
                        Text(p.word,
                            color=if(isSelected) Color.White else if(isLearned) catColor else TxtSecondary,
                            style=MaterialTheme.typography.labelLarge,
                            fontWeight=if(isSelected) FontWeight.Bold else FontWeight.Normal)
                    }
                }
            }
        }

        if(prep == null) return@Column

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

            // ── Illustration + Title ──────────────────────────────────
            Card(Modifier.fillMaxWidth().padding(16.dp),
                colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(20.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Row(verticalAlignment=Alignment.CenterVertically) {
                        // Large illustration
                        PrepIllustration(prep.imageType, catColor, Modifier.size(120.dp))
                        Spacer(Modifier.width(16.dp))
                        Column {
                            Text(prep.word,
                                style=MaterialTheme.typography.displayLarge,
                                color=catColor, fontWeight=FontWeight.ExtraBold)
                            Spacer(Modifier.height(4.dp))
                            Surface(shape=RoundedCornerShape(8.dp), color=catColor.copy(0.15f)) {
                                Text(prep.meaning, modifier=Modifier.padding(10.dp,5.dp),
                                    style=MaterialTheme.typography.titleMedium, color=catColor)
                            }
                            Spacer(Modifier.height(8.dp))
                            Text("Structure:", style=MaterialTheme.typography.labelLarge, color=TxtHint)
                            Text(prep.structure, style=MaterialTheme.typography.bodySmall,
                                fontFamily=androidx.compose.ui.text.font.FontFamily.Monospace,
                                color=Cyan)
                        }
                    }
                }
            }

            // ── Definition ────────────────────────────────────────────
            Card(Modifier.fillMaxWidth().padding(horizontal=16.dp),
                colors=CardDefaults.cardColors(BgCard2), shape=RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Row(verticalAlignment=Alignment.CenterVertically) {
                        Icon(Icons.Filled.Info, null, tint=catColor, modifier=Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("সংজ্ঞা", style=MaterialTheme.typography.titleMedium, color=catColor)
                    }
                    Spacer(Modifier.height(10.dp))
                    Text(prep.definition, style=MaterialTheme.typography.bodyMedium)
                    if(prep.notes.isNotBlank()) {
                        Spacer(Modifier.height(10.dp))
                        Surface(shape=RoundedCornerShape(8.dp), color=Gold.copy(0.08f),
                            border=BorderStroke(1.dp,Gold.copy(0.25f))) {
                            Row(Modifier.padding(10.dp), verticalAlignment=Alignment.Top) {
                                Icon(Icons.Filled.Lightbulb, null, tint=Gold, modifier=Modifier.size(16.dp))
                                Spacer(Modifier.width(8.dp))
                                Text(prep.notes, style=MaterialTheme.typography.bodySmall, color=Gold.copy(0.9f))
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // ── Examples ──────────────────────────────────────────────
            Card(Modifier.fillMaxWidth().padding(horizontal=16.dp),
                colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Row(verticalAlignment=Alignment.CenterVertically) {
                        Icon(Icons.Filled.FormatListBulleted, null, tint=catColor, modifier=Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("উদাহরণ", style=MaterialTheme.typography.titleMedium, color=catColor)
                    }
                    Spacer(Modifier.height(12.dp))
                    prep.examples.forEach { ex ->
                        Surface(Modifier.fillMaxWidth().padding(vertical=5.dp),
                            color=BgDeep, shape=RoundedCornerShape(12.dp),
                            border=BorderStroke(1.dp, catColor.copy(0.2f))) {
                            Column(Modifier.padding(14.dp)) {
                                // Highlight the preposition in the sentence
                                val words = ex.sentence.split(" ")
                                val annotated = buildAnnotatedString {
                                    words.forEachIndexed { i, word ->
                                        val clean = word.replace(Regex("[.,!?]"),"").lowercase()
                                        val prepWords = prep.word.split(" ").map{it.lowercase()}
                                        if(prepWords.any{clean.contains(it)}) {
                                            withStyle(SpanStyle(color=catColor, fontWeight=FontWeight.ExtraBold,
                                                background=catColor.copy(0.15f))) { append(word) }
                                        } else append(word)
                                        if(i<words.size-1) append(" ")
                                    }
                                }
                                Text(annotated, style=MaterialTheme.typography.bodyLarge)
                                Spacer(Modifier.height(4.dp))
                                Text(ex.translation, style=MaterialTheme.typography.bodySmall,
                                    color=TxtHint)
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // ── Mark as Learned ───────────────────────────────────────
            val isLearned = prep.id in learnedIds
            Button(
                onClick = { if(!isLearned) vm.markLearned(prep) },
                modifier = Modifier.fillMaxWidth().padding(horizontal=16.dp).height(54.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(isLearned) Mint else catColor
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(if(isLearned) Icons.Filled.CheckCircle else Icons.Filled.School, null)
                Spacer(Modifier.width(10.dp))
                Text(if(isLearned) "শেখা হয়ে গেছে! +15 XP" else "শেখা সম্পন্ন — +15 XP পাও",
                    fontWeight=FontWeight.Bold, fontSize=16.sp)
            }

            // Next/Prev
            Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement=Arrangement.spacedBy(10.dp)) {
                if(selectedIdx>0) OutlinedButton(
                    onClick={selectedIdx--}, modifier=Modifier.weight(1f).height(48.dp),
                    shape=RoundedCornerShape(12.dp), border=BorderStroke(1.dp,Divider)) {
                    Icon(Icons.Filled.ChevronLeft,null,tint=TxtSecondary,modifier=Modifier.size(18.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("আগেরটা", color=TxtSecondary)
                } else Spacer(Modifier.weight(1f))

                if(selectedIdx<cat.prepositions.size-1) Button(
                    onClick={selectedIdx++}, modifier=Modifier.weight(1f).height(48.dp),
                    colors=ButtonDefaults.buttonColors(catColor), shape=RoundedCornerShape(12.dp)) {
                    Text("পরেরটা")
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.Filled.ChevronRight,null,modifier=Modifier.size(18.dp))
                } else Spacer(Modifier.weight(1f))
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

package com.prepmaster.app.ui.screens.stories

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prepmaster.app.ui.AppViewModel
import com.prepmaster.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoriesScreen(vm: AppViewModel = viewModel()) {
    val stories = vm.stories
    var selectedStory by remember { mutableStateOf<com.prepmaster.app.data.model.StoryItem?>(null) }

    if (selectedStory != null) {
        StoryReadScreen(story=selectedStory!!, onBack={selectedStory=null})
        return
    }

    Column(Modifier.fillMaxSize().background(BgDeep)) {
        Surface(color=BgSurface, shadowElevation=2.dp) {
            Column(Modifier.fillMaxWidth().padding(16.dp,48.dp,16.dp,16.dp)) {
                Row(verticalAlignment=Alignment.CenterVertically) {
                    Icon(Icons.Filled.AutoStories,null,tint=Cyan,modifier=Modifier.size(22.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Preposition Stories", style=MaterialTheme.typography.headlineMedium)
                }
                Spacer(Modifier.height(4.dp))
                Text("গল্পের মধ্যে Preposition খুঁজে বের করো!",
                    style=MaterialTheme.typography.bodySmall, color=TxtSecondary)
            }
        }

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
            stories.forEachIndexed { idx, story ->
                Card(Modifier.fillMaxWidth().padding(vertical=8.dp),
                    colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(18.dp),
                    onClick={selectedStory=story}) {
                    Column(Modifier.padding(20.dp)) {
                        Row(verticalAlignment=Alignment.CenterVertically) {
                            Box(Modifier.size(50.dp).background(
                                Cyan.copy(0.18f), RoundedCornerShape(12.dp)),
                                contentAlignment=Alignment.Center) {
                                Text("${idx+1}", style=MaterialTheme.typography.headlineMedium,
                                    color=Cyan, fontWeight=FontWeight.ExtraBold)
                            }
                            Spacer(Modifier.width(14.dp))
                            Column(Modifier.weight(1f)) {
                                Text(story.title, style=MaterialTheme.typography.titleLarge)
                                Spacer(Modifier.height(4.dp))
                                Text("${story.prepositions.size}টি Preposition • ${story.questions.size}টি প্রশ্ন",
                                    style=MaterialTheme.typography.bodySmall, color=TxtHint)
                            }
                            Icon(Icons.Filled.ChevronRight,null,tint=TxtHint)
                        }
                        Spacer(Modifier.height(12.dp))
                        // Prep chips
                        val uniquePreps = story.prepositions.distinct().take(8)
                        Row(horizontalArrangement=Arrangement.spacedBy(6.dp),
                            modifier=Modifier.horizontalScroll(rememberScrollState())) {
                            uniquePreps.forEach { prep ->
                                Surface(shape=RoundedCornerShape(6.dp), color=Cyan.copy(0.12f)) {
                                    Text(prep, modifier=Modifier.padding(8.dp,3.dp),
                                        style=MaterialTheme.typography.labelSmall, color=Cyan)
                                }
                            }
                            if(story.prepositions.distinct().size>8) {
                                Surface(shape=RoundedCornerShape(6.dp), color=BgElevated) {
                                    Text("+${story.prepositions.distinct().size-8}",
                                        modifier=Modifier.padding(8.dp,3.dp),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoryReadScreen(story: com.prepmaster.app.data.model.StoryItem, onBack: () -> Unit) {
    var showQuiz by remember { mutableStateOf(false) }
    var qIdx     by remember { mutableIntStateOf(0) }
    var selOpt   by remember(qIdx) { mutableStateOf<Int?>(null) }
    var showAns  by remember(qIdx) { mutableStateOf(false) }
    var qScore   by remember { mutableIntStateOf(0) }
    var qDone    by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().background(BgDeep)) {
        Surface(color=BgSurface, shadowElevation=2.dp) {
            Row(Modifier.fillMaxWidth().padding(4.dp,40.dp,16.dp,8.dp), verticalAlignment=Alignment.CenterVertically) {
                IconButton(onBack){ Icon(Icons.Filled.ArrowBack,null,tint=TxtPrimary) }
                Text(story.title, style=MaterialTheme.typography.titleLarge, modifier=Modifier.weight(1f))
            }
        }

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            if(!showQuiz) {
                // Story text with highlighted prepositions
                Card(Modifier.fillMaxWidth().padding(16.dp),
                    colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(18.dp)) {
                    Column(Modifier.padding(20.dp)) {
                        Row(verticalAlignment=Alignment.CenterVertically) {
                            Icon(Icons.Filled.AutoStories,null,tint=Cyan,modifier=Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("গল্পটি পড়ো", style=MaterialTheme.typography.titleMedium, color=Cyan)
                            Spacer(Modifier.weight(1f))
                            Surface(shape=RoundedCornerShape(6.dp),color=Cyan.copy(0.15f)) {
                                Text("[prep] = Preposition", modifier=Modifier.padding(8.dp,3.dp),
                                    style=MaterialTheme.typography.labelSmall, color=Cyan)
                            }
                        }
                        Spacer(Modifier.height(16.dp))

                        // Build annotated story text
                        val parts = story.content.split(Regex("\\[|\\]"))
                        val annotated = buildAnnotatedString {
                            var toggleBracket = false
                            parts.forEach { part ->
                                if(toggleBracket) {
                                    withStyle(SpanStyle(color=Cyan, fontWeight=FontWeight.ExtraBold,
                                        background=Cyan.copy(0.12f))) { append(part) }
                                } else append(part)
                                toggleBracket = !toggleBracket
                            }
                        }
                        Text(annotated, style=MaterialTheme.typography.bodyLarge, lineHeight=30.sp)
                    }
                }

                // Prepositions used
                Card(Modifier.fillMaxWidth().padding(horizontal=16.dp),
                    colors=CardDefaults.cardColors(BgCard2), shape=RoundedCornerShape(16.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Text("গল্পে ব্যবহৃত Prepositions:", style=MaterialTheme.typography.titleMedium, color=Cyan)
                        Spacer(Modifier.height(10.dp))
                        val unique = story.prepositions.distinct()
                        Row(horizontalArrangement=Arrangement.spacedBy(8.dp),
                            modifier=Modifier.horizontalScroll(rememberScrollState())) {
                            unique.forEach { prep ->
                                Surface(shape=RoundedCornerShape(8.dp), color=Cyan.copy(0.15f),
                                    border=BorderStroke(1.dp,Cyan.copy(0.3f))) {
                                    Text(prep, modifier=Modifier.padding(10.dp,5.dp),
                                        style=MaterialTheme.typography.titleMedium, color=Cyan,
                                        fontWeight=FontWeight.Bold)
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))
                Button(onClick={showQuiz=true},
                    modifier=Modifier.fillMaxWidth().padding(horizontal=16.dp).height(54.dp),
                    colors=ButtonDefaults.buttonColors(Cyan), shape=RoundedCornerShape(16.dp)) {
                    Icon(Icons.Filled.Quiz,null,tint=Color.Black)
                    Spacer(Modifier.width(8.dp))
                    Text("Quiz শুরু করো", fontWeight=FontWeight.Bold, fontSize=16.sp, color=Color.Black)
                }
                Spacer(Modifier.height(24.dp))

            } else {
                // Quiz section
                if(qDone) {
                    Column(Modifier.fillMaxWidth().padding(32.dp),
                        horizontalAlignment=Alignment.CenterHorizontally) {
                        val pct = if(story.questions.isNotEmpty()) qScore*100/story.questions.size else 0
                        val c = when{pct>=80->Mint; pct>=50->Gold; else->Coral}
                        Box(Modifier.size(110.dp).background(c.copy(0.15f),CircleShape),
                            contentAlignment=Alignment.Center) {
                            Column(horizontalAlignment=Alignment.CenterHorizontally) {
                                Text("$pct%", style=MaterialTheme.typography.displayLarge, color=c, fontWeight=FontWeight.ExtraBold)
                                Text("$qScore/${story.questions.size}",style=MaterialTheme.typography.bodySmall, color=TxtSecondary)
                            }
                        }
                        Spacer(Modifier.height(20.dp))
                        Text("Story Quiz সম্পন্ন!", style=MaterialTheme.typography.headlineSmall, color=c)
                        Spacer(Modifier.height(24.dp))
                        Button(onClick=onBack, modifier=Modifier.fillMaxWidth().height(52.dp),
                            colors=ButtonDefaults.buttonColors(Cyan), shape=RoundedCornerShape(14.dp)) {
                            Text("ফিরে যাও", fontWeight=FontWeight.Bold, color=Color.Black)
                        }
                    }
                } else {
                    val q = story.questions[qIdx]
                    Column(Modifier.padding(16.dp)) {
                        Text("প্রশ্ন ${qIdx+1}/${story.questions.size}",
                            style=MaterialTheme.typography.labelLarge, color=Cyan)
                        Spacer(Modifier.height(12.dp))
                        Card(Modifier.fillMaxWidth(), colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(16.dp)) {
                            Text(q.question, Modifier.padding(16.dp), style=MaterialTheme.typography.headlineSmall)
                        }
                        Spacer(Modifier.height(14.dp))
                        q.options.forEachIndexed { idx2, opt ->
                            val isAns = opt==q.answer
                            val isSel = selOpt==idx2
                            val bg = when{!showAns->if(isSel)Cyan.copy(0.18f) else BgCard; isAns->Mint.copy(0.18f); isSel&&!isAns->Coral.copy(0.18f); else->BgCard}
                            val bd = when{!showAns->if(isSel)Cyan else Color.Transparent; isAns->Mint; isSel&&!isAns->Coral; else->Color.Transparent}
                            Card(Modifier.fillMaxWidth().padding(vertical=4.dp).border(1.5.dp,bd,RoundedCornerShape(12.dp)),
                                colors=CardDefaults.cardColors(bg), shape=RoundedCornerShape(12.dp),
                                onClick={if(!showAns) selOpt=idx2}) {
                                Text(opt, Modifier.padding(14.dp), style=MaterialTheme.typography.bodyMedium)
                            }
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(onClick={
                            if(!showAns&&selOpt!=null){showAns=true; if(q.options[selOpt!!]==q.answer) qScore++}
                            else if(showAns){if(qIdx+1<story.questions.size){qIdx++;selOpt=null;showAns=false}else qDone=true}
                        }, modifier=Modifier.fillMaxWidth().height(52.dp),
                            enabled=selOpt!=null||showAns,
                            colors=ButtonDefaults.buttonColors(Cyan), shape=RoundedCornerShape(14.dp)) {
                            Text(when{!showAns&&selOpt!=null->"উত্তর দেখো";showAns&&qIdx+1<story.questions.size->"পরের প্রশ্ন";showAns->"ফলাফল";else->"বিকল্প বেছে নাও"},
                                fontWeight=FontWeight.Bold, color=Color.Black)
                        }
                    }
                }
            }
        }
    }
}

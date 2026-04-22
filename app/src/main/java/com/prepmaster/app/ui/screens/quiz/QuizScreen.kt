package com.prepmaster.app.ui.screens.quiz

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prepmaster.app.data.model.QuizQuestion
import com.prepmaster.app.ui.AppViewModel
import com.prepmaster.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(vm: AppViewModel = viewModel()) {
    val allQ = vm.quizQuestions.shuffled()
    var catFilter by remember { mutableStateOf("all") }
    val cats = listOf("all","place","time","movement","manner","cause","agent","possession","compound")
    val catNames = mapOf("all" to "সব","place" to "অবস্থান","time" to "সময়",
        "movement" to "গতি","manner" to "পদ্ধতি","cause" to "কারণ",
        "agent" to "কর্তা","possession" to "মালিকানা","compound" to "যৌগিক")
    val questions = if(catFilter=="all") allQ else allQ.filter{it.categoryId==catFilter}

    var current   by remember(catFilter) { mutableIntStateOf(0) }
    var selected  by remember(current,catFilter) { mutableStateOf<Int?>(null) }
    var showRes   by remember(current,catFilter) { mutableStateOf(false) }
    var score     by remember(catFilter) { mutableIntStateOf(0) }
    var done      by remember(catFilter) { mutableStateOf(false) }

    if(questions.isEmpty()) {
        Box(Modifier.fillMaxSize().background(BgDeep), contentAlignment=Alignment.Center) {
            Text("প্রশ্ন নেই", color=TxtHint) }
        return
    }

    if(done) {
        QuizResult(score,questions.size,
            onRetry={ score=0; current=0; selected=null; showRes=false; done=false },
            onRecord={ vm.recordQuiz(score, questions.size) })
        return
    }

    val q = questions[current]
    val isCorrect = selected == q.correctIdx

    Column(Modifier.fillMaxSize().background(BgDeep)) {
        // Header
        Surface(color=BgSurface, shadowElevation=2.dp) {
            Column(Modifier.fillMaxWidth().padding(16.dp,48.dp,16.dp,10.dp)) {
                Row(verticalAlignment=Alignment.CenterVertically) {
                    Icon(Icons.Filled.Quiz,null,tint=Coral,modifier=Modifier.size(22.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Quiz", style=MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.weight(1f))
                    Surface(shape=RoundedCornerShape(20.dp), color=Mint.copy(0.15f)) {
                        Text("$score/${questions.size}", modifier=Modifier.padding(10.dp,4.dp),
                            color=Mint, fontWeight=FontWeight.Bold, style=MaterialTheme.typography.labelLarge)
                    }
                }
                Spacer(Modifier.height(10.dp))
                // Category filter
                Row(horizontalArrangement=Arrangement.spacedBy(6.dp),
                    modifier=Modifier.horizontalScroll(rememberScrollState())) {
                    cats.forEach { cat ->
                        val sel = catFilter==cat
                        Surface(shape=RoundedCornerShape(16.dp),
                            color=if(sel) Coral else BgCard,
                            border=if(!sel) BorderStroke(1.dp,Divider) else null,
                            modifier=Modifier.clickable{catFilter=cat; current=0; score=0; selected=null; showRes=false; done=false}) {
                            Text(catNames[cat]?:cat, modifier=Modifier.padding(12.dp,5.dp),
                                color=if(sel) Color.White else TxtSecondary,
                                style=MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }
        }

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
            // Progress
            Text("প্রশ্ন ${current+1} / ${questions.size}", style=MaterialTheme.typography.labelLarge, color=Coral)
            Spacer(Modifier.height(6.dp))
            LinearProgressIndicator(
                progress={(current+1).toFloat()/questions.size},
                modifier=Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                color=Coral, trackColor=BgElevated)
            Spacer(Modifier.height(20.dp))

            // Question
            Card(modifier=Modifier.fillMaxWidth(), colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(18.dp)) {
                Column(Modifier.padding(20.dp)) {
                    Surface(shape=RoundedCornerShape(6.dp), color=Coral.copy(0.15f)) {
                        Text(catNames[q.categoryId]?:q.categoryId, modifier=Modifier.padding(8.dp,3.dp),
                            style=MaterialTheme.typography.labelSmall, color=Coral)
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(q.question, style=MaterialTheme.typography.headlineSmall, lineHeight=30.sp)
                }
            }
            Spacer(Modifier.height(18.dp))

            // Options
            q.options.forEachIndexed { idx, opt ->
                val bg = when {
                    !showRes -> if(selected==idx) Coral.copy(0.18f) else BgCard
                    idx==q.correctIdx -> Mint.copy(0.18f)
                    idx==selected && !isCorrect -> Coral.copy(0.18f)
                    else -> BgCard
                }
                val border = when {
                    !showRes -> if(selected==idx) Coral else Color.Transparent
                    idx==q.correctIdx -> Mint
                    idx==selected && !isCorrect -> Coral
                    else -> Color.Transparent
                }
                Card(modifier=Modifier.fillMaxWidth().padding(vertical=5.dp)
                    .border(1.5.dp, border, RoundedCornerShape(14.dp)),
                    colors=CardDefaults.cardColors(bg), shape=RoundedCornerShape(14.dp),
                    onClick={if(!showRes) selected=idx}) {
                    Row(Modifier.padding(14.dp), verticalAlignment=Alignment.CenterVertically) {
                        Box(Modifier.size(30.dp).background(BgElevated,CircleShape),
                            contentAlignment=Alignment.Center) {
                            when {
                                showRes && idx==q.correctIdx -> Icon(Icons.Filled.CheckCircle,null,tint=Mint,modifier=Modifier.size(18.dp))
                                showRes && idx==selected && !isCorrect -> Icon(Icons.Filled.Cancel,null,tint=Coral,modifier=Modifier.size(18.dp))
                                else -> Text(('A'+idx).toString(), fontWeight=FontWeight.Bold,
                                    color=if(selected==idx&&!showRes) Coral else TxtSecondary)
                            }
                        }
                        Spacer(Modifier.width(12.dp))
                        Text(opt, style=MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            // Explanation
            AnimatedVisibility(visible=showRes) {
                Card(modifier=Modifier.fillMaxWidth().padding(top=12.dp),
                    colors=CardDefaults.cardColors(if(isCorrect) Mint.copy(0.08f) else Coral.copy(0.08f)),
                    shape=RoundedCornerShape(14.dp),
                    border=BorderStroke(1.dp, if(isCorrect) Mint.copy(0.3f) else Coral.copy(0.3f))) {
                    Row(Modifier.padding(14.dp)) {
                        Icon(if(isCorrect) Icons.Filled.CheckCircle else Icons.Filled.Cancel,
                            null, tint=if(isCorrect) Mint else Coral, modifier=Modifier.size(20.dp))
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(if(isCorrect) "সঠিক উত্তর!" else "ভুল উত্তর!",
                                fontWeight=FontWeight.Bold, color=if(isCorrect) Mint else Coral)
                            Spacer(Modifier.height(4.dp))
                            Text(q.explanation, style=MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick={
                    if(!showRes && selected!=null) {
                        showRes=true
                        if(selected==q.correctIdx) score++
                    } else if(showRes) {
                        if(current+1<questions.size) { current++; selected=null; showRes=false }
                        else { done=true; vm.recordQuiz(score,questions.size) }
                    }
                },
                modifier=Modifier.fillMaxWidth().height(54.dp),
                enabled=selected!=null||showRes,
                colors=ButtonDefaults.buttonColors(Coral),
                shape=RoundedCornerShape(16.dp)
            ) {
                Text(when{!showRes&&selected!=null->"উত্তর যাচাই করো"; showRes&&current+1<questions.size->"পরের প্রশ্ন"; showRes->"ফলাফল দেখো"; else->"বিকল্প বেছে নাও"},
                    fontWeight=FontWeight.Bold, fontSize=16.sp)
            }
        }
    }
}

@Composable
fun QuizResult(score:Int, total:Int, onRetry:()->Unit, onRecord:()->Unit) {
    LaunchedEffect(Unit){onRecord()}
    val pct = if(total>0) score*100/total else 0
    val color = when{ pct>=80->Mint; pct>=50->Gold; else->Coral }
    Column(Modifier.fillMaxSize().background(BgDeep), horizontalAlignment=Alignment.CenterHorizontally,
        verticalArrangement=Arrangement.Center) {
        Box(Modifier.size(140.dp).background(color.copy(0.15f),CircleShape),
            contentAlignment=Alignment.Center) {
            Column(horizontalAlignment=Alignment.CenterHorizontally) {
                Text("$pct%", style=MaterialTheme.typography.displayLarge, color=color, fontWeight=FontWeight.ExtraBold)
                Text("$score/$total", style=MaterialTheme.typography.bodyMedium, color=TxtSecondary)
            }
        }
        Spacer(Modifier.height(28.dp))
        Text(when{pct>=80->"চমৎকার! তুমি অনেক ভালো করেছো!"; pct>=60->"বেশ ভালো! আরেকটু চেষ্টা করো।"; pct>=40->"মন্দ না। আরো Practice করো।"; else->"হতাশ হয়ো না। আবার চেষ্টা করো!"},
            style=MaterialTheme.typography.headlineSmall, color=color, fontWeight=FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        Text("তুমি +${score*10} XP পেয়েছো!", style=MaterialTheme.typography.bodyMedium, color=Gold)
        Spacer(Modifier.height(32.dp))
        Button(onClick=onRetry, modifier=Modifier.padding(horizontal=32.dp).fillMaxWidth().height(54.dp),
            colors=ButtonDefaults.buttonColors(Coral), shape=RoundedCornerShape(16.dp)) {
            Icon(Icons.Filled.Refresh,null)
            Spacer(Modifier.width(8.dp))
            Text("আবার চেষ্টা করো", fontWeight=FontWeight.Bold, fontSize=16.sp)
        }
    }
}

package com.prepmaster.app.ui.screens.practice

import androidx.compose.animation.AnimatedVisibility
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
fun PracticeScreen(vm: AppViewModel = viewModel()) {
    val items  = remember { vm.practiceItems.shuffled() }
    var current  by remember { mutableIntStateOf(0) }
    var selected by remember(current) { mutableStateOf<Int?>(null) }
    var showRes  by remember(current) { mutableStateOf(false) }
    var score    by remember { mutableIntStateOf(0) }
    var done     by remember { mutableStateOf(false) }

    if(items.isEmpty()) { Box(Modifier.fillMaxSize().background(BgDeep),contentAlignment=Alignment.Center){Text("লোড হচ্ছে...",color=TxtHint)}; return }

    if(done) {
        PracticeResult(score,items.size,
            onRetry={ score=0; current=0; selected=null; showRes=false; done=false },
            onRecord={ vm.recordPractice(score,items.size) })
        return
    }

    val item = items[current]
    val isCorrect = selected != null && item.options[selected!!] == item.answer

    Column(Modifier.fillMaxSize().background(BgDeep)) {
        Surface(color=BgSurface, shadowElevation=2.dp) {
            Column(Modifier.fillMaxWidth().padding(16.dp,48.dp,16.dp,14.dp)) {
                Row(verticalAlignment=Alignment.CenterVertically) {
                    Icon(Icons.Filled.Edit,null,tint=Mint,modifier=Modifier.size(22.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Fill in the Blank", style=MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.weight(1f))
                    Surface(shape=RoundedCornerShape(20.dp),color=Mint.copy(0.15f)) {
                        Text("$score/${items.size}", modifier=Modifier.padding(10.dp,4.dp),
                            color=Mint, fontWeight=FontWeight.Bold)
                    }
                }
                Spacer(Modifier.height(10.dp))
                LinearProgressIndicator(
                    progress={(current+1).toFloat()/items.size},
                    modifier=Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                    color=Mint, trackColor=BgElevated)
                Text("${current+1} / ${items.size}", style=MaterialTheme.typography.labelSmall, color=TxtHint)
            }
        }

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {

            // Sentence with blank highlighted
            Card(Modifier.fillMaxWidth(), colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(18.dp)) {
                Column(Modifier.padding(20.dp)) {
                    Text("শূন্যস্থান পূরণ করো:", style=MaterialTheme.typography.labelLarge, color=TxtHint)
                    Spacer(Modifier.height(12.dp))
                    val parts = item.sentence.split("___")
                    val annotated = buildAnnotatedString {
                        parts.forEachIndexed { i, part ->
                            append(part)
                            if(i<parts.size-1) {
                                if(showRes) {
                                    withStyle(SpanStyle(color=if(isCorrect)Mint else Coral,
                                        fontWeight=FontWeight.ExtraBold,
                                        background=if(isCorrect)Mint.copy(0.15f) else Coral.copy(0.15f))) {
                                        append(" ${item.answer} ")
                                    }
                                } else {
                                    withStyle(SpanStyle(color=if(selected!=null)Purple else TxtHint,
                                        fontWeight=FontWeight.ExtraBold,
                                        background=Purple.copy(0.12f))) {
                                        append(if(selected!=null)" ${item.options[selected!!]} " else " _______ ")
                                    }
                                }
                            }
                        }
                    }
                    Text(annotated, style=MaterialTheme.typography.headlineSmall, lineHeight=36.sp)
                    Spacer(Modifier.height(10.dp))
                    Text(item.translation, style=MaterialTheme.typography.bodySmall, color=TxtHint)
                }
            }

            Spacer(Modifier.height(20.dp))

            // Options grid
            val rows = item.options.chunked(2)
            rows.forEach { row ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement=Arrangement.spacedBy(10.dp)) {
                    row.forEach { opt ->
                        val idx = item.options.indexOf(opt)
                        val isOpt = selected==idx
                        val bg = when {
                            !showRes -> if(isOpt) Purple.copy(0.22f) else BgCard
                            opt==item.answer -> Mint.copy(0.22f)
                            isOpt && !isCorrect -> Coral.copy(0.22f)
                            else -> BgCard
                        }
                        val border = when {
                            !showRes -> if(isOpt) Purple else Color.Transparent
                            opt==item.answer -> Mint
                            isOpt && !isCorrect -> Coral
                            else -> Color.Transparent
                        }
                        Card(Modifier.weight(1f).height(52.dp).border(1.5.dp,border,RoundedCornerShape(14.dp)),
                            colors=CardDefaults.cardColors(bg), shape=RoundedCornerShape(14.dp),
                            onClick={if(!showRes) selected=idx}) {
                            Box(Modifier.fillMaxSize(), contentAlignment=Alignment.Center) {
                                Text(opt, fontWeight=FontWeight.Bold, fontSize=16.sp,
                                    color=when{!showRes&&isOpt->Purple; opt==item.answer&&showRes->Mint; isOpt&&showRes&&!isCorrect->Coral; else->TxtSecondary})
                            }
                        }
                    }
                    if(row.size==1) Spacer(Modifier.weight(1f))
                }
                Spacer(Modifier.height(10.dp))
            }

            // Explanation
            AnimatedVisibility(visible=showRes) {
                Card(Modifier.fillMaxWidth(),
                    colors=CardDefaults.cardColors(if(isCorrect)Mint.copy(0.08f) else Coral.copy(0.08f)),
                    shape=RoundedCornerShape(14.dp),
                    border=BorderStroke(1.dp,if(isCorrect)Mint.copy(0.3f) else Coral.copy(0.3f))) {
                    Column(Modifier.padding(14.dp)) {
                        Text(if(isCorrect)"সঠিক!" else "ভুল! সঠিক উত্তর: ${item.answer}",
                            fontWeight=FontWeight.Bold, color=if(isCorrect)Mint else Coral)
                        Spacer(Modifier.height(4.dp))
                        Text(item.explanation, style=MaterialTheme.typography.bodySmall)
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick={
                    if(!showRes && selected!=null) { showRes=true; if(isCorrect) score++ }
                    else if(showRes) {
                        if(current+1<items.size) { current++; selected=null; showRes=false }
                        else { done=true; vm.recordPractice(score,items.size) }
                    }
                },
                modifier=Modifier.fillMaxWidth().height(54.dp),
                enabled=selected!=null||showRes,
                colors=ButtonDefaults.buttonColors(Mint),
                shape=RoundedCornerShape(16.dp)
            ) {
                Text(when{!showRes&&selected!=null->"উত্তর চেক করো"; showRes&&current+1<items.size->"পরেরটা"; showRes->"ফলাফল দেখো"; else->"বিকল্প বেছে নাও"},
                    fontWeight=FontWeight.Bold, fontSize=16.sp)
            }
        }
    }
}

@Composable
fun PracticeResult(score:Int,total:Int,onRetry:()->Unit,onRecord:()->Unit) {
    LaunchedEffect(Unit){onRecord()}
    val pct = if(total>0) score*100/total else 0
    val c = when{pct>=80->Mint; pct>=50->Gold; else->Coral}
    Column(Modifier.fillMaxSize().background(BgDeep), horizontalAlignment=Alignment.CenterHorizontally,
        verticalArrangement=Arrangement.Center) {
        Box(Modifier.size(130.dp).background(c.copy(0.15f),CircleShape), contentAlignment=Alignment.Center) {
            Column(horizontalAlignment=Alignment.CenterHorizontally) {
                Text("$pct%", style=MaterialTheme.typography.displayLarge, color=c, fontWeight=FontWeight.ExtraBold)
                Text("$score/$total", style=MaterialTheme.typography.bodyMedium, color=TxtSecondary)
            }
        }
        Spacer(Modifier.height(24.dp))
        Text(when{pct>=80->"দুর্দান্ত Practice!"; pct>=60->"ভালো করেছো!"; else->"আরো Practice করো!"},
            style=MaterialTheme.typography.headlineSmall, color=c, fontWeight=FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("+${score*5} XP অর্জন!", style=MaterialTheme.typography.bodyMedium, color=Gold)
        Spacer(Modifier.height(32.dp))
        Button(onClick=onRetry, modifier=Modifier.padding(32.dp).fillMaxWidth().height(54.dp),
            colors=ButtonDefaults.buttonColors(Mint), shape=RoundedCornerShape(16.dp)) {
            Icon(Icons.Filled.Refresh,null); Spacer(Modifier.width(8.dp))
            Text("আবার চেষ্টা", fontWeight=FontWeight.Bold, fontSize=16.sp)
        }
    }
}

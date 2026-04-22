package com.prepmaster.app.ui.screens.flashcard

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prepmaster.app.ui.AppViewModel
import com.prepmaster.app.ui.components.PrepIllustration
import com.prepmaster.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCardScreen(vm: AppViewModel = viewModel()) {
    val cards = remember { vm.allFlashCards }
    var idx     by remember { mutableIntStateOf(0) }
    var flipped by remember(idx) { mutableStateOf(false) }
    var viewed  by remember { mutableIntStateOf(0) }

    val card = cards.getOrNull(idx)
    val color = card?.let { Color(it.color) } ?: Purple

    // Flip animation
    val rotation by animateFloatAsState(
        targetValue = if(flipped) 180f else 0f,
        animationSpec = tween(400, easing=FastOutSlowInEasing), label="flip")

    Column(Modifier.fillMaxSize().background(BgDeep)) {
        Surface(color=BgSurface, shadowElevation=2.dp) {
            Column(Modifier.fillMaxWidth().padding(16.dp,48.dp,16.dp,14.dp)) {
                Row(verticalAlignment=Alignment.CenterVertically) {
                    Icon(Icons.Filled.Style,null,tint=Gold,modifier=Modifier.size(22.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Flashcard", style=MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.weight(1f))
                    Text("${idx+1}/${cards.size}", style=MaterialTheme.typography.bodySmall, color=TxtHint)
                }
                Spacer(Modifier.height(10.dp))
                LinearProgressIndicator(
                    progress={(idx+1).toFloat()/cards.size},
                    modifier=Modifier.fillMaxWidth().height(5.dp).clip(RoundedCornerShape(3.dp)),
                    color=Gold, trackColor=BgElevated)
            }
        }

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            horizontalAlignment=Alignment.CenterHorizontally) {

            Spacer(Modifier.height(24.dp))

            if(card!=null) {
                // Flip card
                Box(Modifier.padding(horizontal=24.dp).fillMaxWidth().height(340.dp)
                    .clickable{ flipped=!flipped; if(!flipped) { vm.recordFlashcard(); viewed++ } }
                    .graphicsLayer{ rotationY=rotation; cameraDistance=12f*density }
                ) {
                    if(rotation <= 90f) {
                        // Front face
                        Card(Modifier.fillMaxSize(),
                            colors=CardDefaults.cardColors(BgCard),
                            shape=RoundedCornerShape(24.dp),
                            elevation=CardDefaults.cardElevation(8.dp)) {
                            Column(Modifier.fillMaxSize().padding(24.dp),
                                horizontalAlignment=Alignment.CenterHorizontally,
                                verticalArrangement=Arrangement.Center) {
                                Surface(shape=RoundedCornerShape(8.dp), color=color.copy(0.15f)) {
                                    Text(card.categoryId.uppercase(), modifier=Modifier.padding(10.dp,4.dp),
                                        style=MaterialTheme.typography.labelSmall, color=color)
                                }
                                Spacer(Modifier.height(24.dp))
                                PrepIllustration(card.imageType, color, Modifier.size(130.dp))
                                Spacer(Modifier.height(24.dp))
                                Text(card.prep,
                                    style=MaterialTheme.typography.displayLarge,
                                    color=color, fontWeight=FontWeight.ExtraBold)
                                Spacer(Modifier.height(12.dp))
                                Surface(shape=RoundedCornerShape(6.dp), color=color.copy(0.1f)) {
                                    Text("ট্যাপ করে অর্থ দেখো", modifier=Modifier.padding(12.dp,5.dp),
                                        style=MaterialTheme.typography.labelLarge, color=color)
                                }
                            }
                        }
                    } else {
                        // Back face
                        Card(Modifier.fillMaxSize().graphicsLayer{rotationY=180f},
                            colors=CardDefaults.cardColors(
                                containerColor=Color(0xFF0A0A20).copy(0.95f)),
                            shape=RoundedCornerShape(24.dp),
                            border=BorderStroke(2.dp, color.copy(0.5f)),
                            elevation=CardDefaults.cardElevation(8.dp)) {
                            Column(Modifier.fillMaxSize().padding(24.dp),
                                horizontalAlignment=Alignment.CenterHorizontally,
                                verticalArrangement=Arrangement.Center) {
                                Text(card.prep, style=MaterialTheme.typography.headlineLarge,
                                    color=color, fontWeight=FontWeight.ExtraBold)
                                Spacer(Modifier.height(16.dp))
                                Surface(shape=RoundedCornerShape(12.dp), color=color.copy(0.18f)) {
                                    Text(card.meaning, modifier=Modifier.padding(16.dp,8.dp),
                                        style=MaterialTheme.typography.headlineSmall, color=color)
                                }
                                Spacer(Modifier.height(20.dp))
                                Surface(shape=RoundedCornerShape(12.dp),
                                    color=BgCard, border=BorderStroke(1.dp,Divider)) {
                                    Column(Modifier.padding(16.dp)) {
                                        Row(verticalAlignment=Alignment.CenterVertically) {
                                            Icon(Icons.Filled.FormatQuote,null,tint=color,modifier=Modifier.size(16.dp))
                                            Spacer(Modifier.width(6.dp))
                                            Text("উদাহরণ", style=MaterialTheme.typography.labelLarge, color=color)
                                        }
                                        Spacer(Modifier.height(6.dp))
                                        Text(card.example, style=MaterialTheme.typography.bodyMedium)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(28.dp))

            // Navigation buttons
            Row(Modifier.fillMaxWidth().padding(horizontal=24.dp), horizontalArrangement=Arrangement.spacedBy(14.dp)) {
                OutlinedButton(onClick={ if(idx>0){ idx--; flipped=false } },
                    modifier=Modifier.weight(1f).height(52.dp), shape=RoundedCornerShape(14.dp),
                    enabled=idx>0, border=BorderStroke(1.dp,if(idx>0)Gold else Divider)) {
                    Icon(Icons.Filled.ChevronLeft,null,tint=if(idx>0)Gold else TxtHint)
                    Spacer(Modifier.width(4.dp))
                    Text("আগের", color=if(idx>0)Gold else TxtHint, fontWeight=FontWeight.SemiBold)
                }
                Button(onClick={ if(idx<cards.size-1){ idx++; flipped=false; vm.recordFlashcard(); viewed++ } },
                    modifier=Modifier.weight(1f).height(52.dp),
                    enabled=idx<cards.size-1,
                    colors=ButtonDefaults.buttonColors(if(idx<cards.size-1)Gold else BgElevated),
                    shape=RoundedCornerShape(14.dp)) {
                    Text("পরের", color=if(idx<cards.size-1) Color.Black else TxtHint, fontWeight=FontWeight.Bold)
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.Filled.ChevronRight,null,tint=if(idx<cards.size-1) Color.Black else TxtHint)
                }
            }

            Spacer(Modifier.height(16.dp))
            Text("দেখা হয়েছে: $viewed টি • +${viewed*2} XP",
                style=MaterialTheme.typography.bodySmall, color=TxtHint)
            Spacer(Modifier.height(24.dp))
        }
    }
}

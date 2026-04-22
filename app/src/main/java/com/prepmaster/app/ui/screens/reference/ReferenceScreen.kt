package com.prepmaster.app.ui.screens.reference

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prepmaster.app.ui.AppViewModel
import com.prepmaster.app.ui.components.PrepIllustration
import com.prepmaster.app.ui.theme.*

@Composable
fun ReferenceScreen(vm: AppViewModel = viewModel()) {
    val cats = vm.categories
    var search  by remember { mutableStateOf("") }
    var selCat  by remember { mutableStateOf("all") }
    var expanded by remember { mutableStateOf<String?>(null) }

    val allPreps = cats.flatMap { cat -> cat.prepositions.map { it to cat } }
    val filtered = allPreps.filter { (prep, cat) ->
        val matchSearch = search.isEmpty() ||
            prep.word.contains(search,true) || prep.meaning.contains(search,true) ||
            prep.definition.contains(search,true)
        val matchCat = selCat=="all" || cat.id==selCat
        matchSearch && matchCat
    }

    Column(Modifier.fillMaxSize().background(BgDeep)) {
        Surface(color=BgSurface, shadowElevation=2.dp) {
            Column(Modifier.fillMaxWidth().padding(16.dp,48.dp,16.dp,10.dp)) {
                Row(verticalAlignment=Alignment.CenterVertically) {
                    Icon(Icons.Filled.LibraryBooks,null,tint=Purple,modifier=Modifier.size(22.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Reference", style=MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.weight(1f))
                    Text("${filtered.size} টি", style=MaterialTheme.typography.bodySmall, color=TxtHint)
                }
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(value=search, onValueChange={search=it},
                    modifier=Modifier.fillMaxWidth(),
                    placeholder={Text("Preposition খুঁজুন...", color=TxtHint)},
                    leadingIcon={Icon(Icons.Filled.Search,null,tint=TxtHint)},
                    trailingIcon={if(search.isNotEmpty()) { IconButton(onClick={search=""}) {Icon(Icons.Filled.Clear,null,tint=TxtHint)} }},
                    colors=OutlinedTextFieldDefaults.colors(
                        focusedBorderColor=Purple, unfocusedBorderColor=Divider,
                        focusedContainerColor=BgCard, unfocusedContainerColor=BgCard),
                    shape=RoundedCornerShape(14.dp), singleLine=true)
            }
        }

        // Category filter
        LazyRow(contentPadding=PaddingValues(horizontal=14.dp,vertical=8.dp),
            horizontalArrangement=Arrangement.spacedBy(8.dp)) {
            item {
                Surface(shape=RoundedCornerShape(18.dp),
                    color=if(selCat=="all") Purple else BgCard,
                    border=if(selCat!="all") BorderStroke(1.dp,Divider) else null,
                    modifier=Modifier.clickable{selCat="all"}) {
                    Text("সব", modifier=Modifier.padding(14.dp,6.dp),
                        color=if(selCat=="all") Color.White else TxtSecondary,
                        style=MaterialTheme.typography.labelLarge)
                }
            }
            items(cats) { cat ->
                val catColor = Color(cat.color)
                val sel = selCat==cat.id
                Surface(shape=RoundedCornerShape(18.dp),
                    color=if(sel) catColor else BgCard,
                    border=if(!sel) BorderStroke(1.dp,Divider) else null,
                    modifier=Modifier.clickable{selCat=cat.id}) {
                    Text(cat.titleBn, modifier=Modifier.padding(14.dp,6.dp),
                        color=if(sel) Color.White else TxtSecondary,
                        style=MaterialTheme.typography.labelLarge)
                }
            }
        }

        LazyColumn(contentPadding=PaddingValues(16.dp), verticalArrangement=Arrangement.spacedBy(8.dp)) {
            items(filtered) { (prep, cat) ->
                val isEx = expanded==prep.id
                val catColor = Color(cat.color)
                Card(colors=CardDefaults.cardColors(BgCard), shape=RoundedCornerShape(14.dp),
                    modifier=Modifier.fillMaxWidth()) {
                    Column {
                        Row(Modifier.fillMaxWidth().clickable{expanded=if(isEx) null else prep.id}
                            .padding(14.dp), verticalAlignment=Alignment.CenterVertically) {
                            // Illustration thumbnail
                            PrepIllustration(prep.imageType, catColor, Modifier.size(48.dp))
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f)) {
                                Row(verticalAlignment=Alignment.CenterVertically) {
                                    Surface(shape=RoundedCornerShape(6.dp), color=catColor.copy(0.15f)) {
                                        Text(prep.word, modifier=Modifier.padding(8.dp,3.dp),
                                            style=MaterialTheme.typography.titleLarge, color=catColor,
                                            fontWeight=FontWeight.ExtraBold)
                                    }
                                    Spacer(Modifier.width(8.dp))
                                    Text(prep.meaning, style=MaterialTheme.typography.bodyMedium)
                                }
                                Spacer(Modifier.height(4.dp))
                                Text(prep.definition, style=MaterialTheme.typography.bodySmall,
                                    maxLines=if(isEx) Int.MAX_VALUE else 1,
                                    overflow=androidx.compose.ui.text.style.TextOverflow.Ellipsis)
                            }
                            Icon(if(isEx) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                                null, tint=TxtHint)
                        }
                        if(isEx) {
                            Divider(color=Divider)
                            Column(Modifier.padding(14.dp)) {
                                // Structure
                                Surface(shape=RoundedCornerShape(6.dp), color=BgDeep,
                                    border=BorderStroke(1.dp,Cyan.copy(0.3f))) {
                                    Text("Structure: ${prep.structure}",
                                        modifier=Modifier.padding(10.dp,6.dp),
                                        style=MaterialTheme.typography.bodySmall,
                                        fontFamily=androidx.compose.ui.text.font.FontFamily.Monospace,
                                        color=Cyan)
                                }
                                Spacer(Modifier.height(10.dp))
                                // Examples
                                prep.examples.forEach { ex ->
                                    Surface(Modifier.fillMaxWidth().padding(vertical=4.dp),
                                        color=BgDeep, shape=RoundedCornerShape(10.dp),
                                        border=BorderStroke(1.dp,catColor.copy(0.2f))) {
                                        Column(Modifier.padding(12.dp)) {
                                            Text(ex.sentence, style=MaterialTheme.typography.bodyMedium, fontWeight=FontWeight.Medium)
                                            Text(ex.translation, style=MaterialTheme.typography.bodySmall, color=TxtHint)
                                        }
                                    }
                                }
                                if(prep.notes.isNotBlank()) {
                                    Spacer(Modifier.height(8.dp))
                                    Surface(shape=RoundedCornerShape(8.dp), color=Gold.copy(0.08f)) {
                                        Row(Modifier.padding(10.dp)) {
                                            Icon(Icons.Filled.Lightbulb,null,tint=Gold,modifier=Modifier.size(14.dp))
                                            Spacer(Modifier.width(6.dp))
                                            Text(prep.notes, style=MaterialTheme.typography.bodySmall, color=Gold.copy(0.9f))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

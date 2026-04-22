package com.prepmaster.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    displayLarge  = TextStyle(fontSize=36.sp, fontWeight=FontWeight.ExtraBold, color=TxtPrimary, letterSpacing=(-0.5).sp),
    headlineLarge  = TextStyle(fontSize=28.sp, fontWeight=FontWeight.Bold,      color=TxtPrimary),
    headlineMedium = TextStyle(fontSize=22.sp, fontWeight=FontWeight.Bold,      color=TxtPrimary),
    headlineSmall  = TextStyle(fontSize=18.sp, fontWeight=FontWeight.SemiBold,  color=TxtPrimary),
    titleLarge     = TextStyle(fontSize=16.sp, fontWeight=FontWeight.SemiBold,  color=TxtPrimary),
    titleMedium    = TextStyle(fontSize=14.sp, fontWeight=FontWeight.Medium,    color=TxtPrimary),
    bodyLarge      = TextStyle(fontSize=16.sp, fontWeight=FontWeight.Normal,    color=TxtSecondary, lineHeight=26.sp),
    bodyMedium     = TextStyle(fontSize=14.sp, fontWeight=FontWeight.Normal,    color=TxtSecondary, lineHeight=24.sp),
    bodySmall      = TextStyle(fontSize=12.sp, fontWeight=FontWeight.Normal,    color=TxtHint,      lineHeight=20.sp),
    labelLarge     = TextStyle(fontSize=12.sp, fontWeight=FontWeight.SemiBold,  color=TxtSecondary, letterSpacing=0.4.sp),
    labelSmall     = TextStyle(fontSize=10.sp, fontWeight=FontWeight.Medium,    color=TxtHint,      letterSpacing=0.5.sp),
)

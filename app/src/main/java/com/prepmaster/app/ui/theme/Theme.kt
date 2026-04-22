package com.prepmaster.app.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary         = Purple,
    onPrimary       = TxtPrimary,
    primaryContainer= PurpleDark,
    secondary       = Cyan,
    onSecondary     = TxtPrimary,
    tertiary        = Coral,
    background      = BgDeep,
    onBackground    = TxtPrimary,
    surface         = BgSurface,
    onSurface       = TxtPrimary,
    surfaceVariant  = BgCard,
    onSurfaceVariant= TxtSecondary,
    error           = Coral,
    outline         = Divider
)

@Composable
fun PrepMasterTheme(content: @Composable () -> Unit) =
    MaterialTheme(colorScheme = DarkColors, typography = AppTypography, content = content)

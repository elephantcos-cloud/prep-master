package com.prepmaster.app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.prepmaster.app.ui.navigation.AppNavGraph
import com.prepmaster.app.ui.theme.PrepMasterTheme
import com.prepmaster.app.ui.theme.BgDeep

class MainActivity : ComponentActivity() {
    private val notifLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) {}

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            notifLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        setContent {
            PrepMasterTheme {
                Surface(Modifier.fillMaxSize(), color = BgDeep) {
                    AppNavGraph()
                }
            }
        }
    }
}

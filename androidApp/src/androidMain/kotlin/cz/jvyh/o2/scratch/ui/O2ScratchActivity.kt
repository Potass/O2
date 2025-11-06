package cz.jvyh.o2.scratch.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cz.jvyh.o2.scratch.common.ui.AppContent
import cz.jvyh.o2.scratch.common.ui.InitAppContent

class O2ScratchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        setContent {
            InitAppContent {
                AppContent()
            }
        }
    }
}
package com.digitalguardian.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF07111F)
private val Cyan = Color(0xFF67E8F9)
private val Red = Color(0xFFEF4444)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { GuardianApp() } }
    }
}

@Composable
fun GuardianApp() {
    var armed by remember { mutableStateOf(true) }
    var status by remember { mutableStateOf("Protection active") }
    Surface(modifier = Modifier.fillMaxSize(), color = Navy) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("DIGITAL GUARDIAN", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Switch(checked = armed, onCheckedChange = { armed = it; status = if (it) "Protection active" else "Protection paused" })
            }
            Spacer(Modifier.height(48.dp))
            Box(Modifier.size(94.dp).background(Cyan.copy(alpha = .14f), CircleShape), contentAlignment = Alignment.Center) {
                Text("SOS", color = Cyan, fontWeight = FontWeight.Black, fontSize = 26.sp)
            }
            Spacer(Modifier.height(22.dp))
            Text(status, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 28.sp, textAlign = TextAlign.Center)
            Text("Your trusted circle can be alerted from one discreet safety action.", color = Color(0xFF94A3B8), textAlign = TextAlign.Center, modifier = Modifier.padding(top = 10.dp))
            Spacer(Modifier.height(38.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                InfoCard("3", "Trusted peers", Modifier.weight(1f))
                InfoCard("ON", "Location", Modifier.weight(1f))
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = { if (armed) status = "SOS test activated" },
                colors = ButtonDefaults.buttonColors(containerColor = Red),
                shape = RoundedCornerShape(22.dp),
                modifier = Modifier.fillMaxWidth().height(74.dp)
            ) { Text("HOLD FOR SOS", fontWeight = FontWeight.Black, fontSize = 20.sp) }
            Spacer(Modifier.height(14.dp))
            OutlinedButton(onClick = { status = "Test alert ready" }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp)) {
                Text("Test safety alert", color = Cyan)
            }
            Spacer(Modifier.height(18.dp))
            Text("Prototype: no real emergency message is sent yet.", color = Color(0xFF64748B), fontSize = 12.sp)
        }
    }
}

@Composable
private fun InfoCard(value: String, label: String, modifier: Modifier = Modifier) {
    ElevatedCard(modifier = modifier, colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFF0F1B2D)), shape = RoundedCornerShape(20.dp)) {
        Column(Modifier.padding(18.dp)) {
            Text(value, color = Cyan, fontWeight = FontWeight.Black, fontSize = 24.sp)
            Text(label, color = Color(0xFF94A3B8), fontSize = 13.sp)
        }
    }
}

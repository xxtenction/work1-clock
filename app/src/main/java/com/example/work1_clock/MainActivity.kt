package com.example.work1_clock
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.work1_clock.ui.theme.Work1clockTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Work1clockTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ClockScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClockScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var currentTime by remember { mutableStateOf(getFormattedTime()) }
    var currentDate by remember { mutableStateOf(getFormattedDate()) }
    var isDarkTheme by remember { mutableStateOf(false) }

    // 每秒更新时间
    LaunchedEffect(Unit) {
        scope.launch {
            while (true) {
                currentTime = getFormattedTime()
                currentDate = getFormattedDate()
                delay(1000)
            }
        }
    }

    // 主布局
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // 时钟卡片
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .shadow(10.dp, RoundedCornerShape(20.dp))
                .clickable { isDarkTheme = !isDarkTheme },
            colors = CardDefaults.cardColors(
                containerColor = if (isDarkTheme) Color(0xFF1E1E1E) else Color(0xFFF5F5F5)
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // 星期
                Text(
                    text = getDayOfWeek(),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (isDarkTheme) Color(0xFFAAAAAA) else Color(0xFFC70404)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 时间
                Text(
                    text = currentTime,
                    style = TextStyle(
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isDarkTheme) Color.White else Color.Black,
                        shadow = Shadow(
                            color = Color(0x33000000),
                            blurRadius = 10f,
                            offset = androidx.compose.ui.geometry.Offset(4f, 4f)
                        )
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 日期
                Text(
                    text = currentDate,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = if (isDarkTheme) Color(0xFFAAAAAA) else Color(0xFF666666)
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // 主题切换按钮
                IconButton(
                    onClick = { isDarkTheme = !isDarkTheme },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RectangleShape)
                        .background(
                            if (isDarkTheme)
                                Color(0xFF2D2D2D)
                            else
                                Color(0xFFE0E0E0)
                        )
                ) {
                    Icon(
                        imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                        contentDescription = "切换主题",
                        tint = if (isDarkTheme) Color(0xFFFFD700) else Color(0xFF333333)
                    )
                }
            }
        }
    }
}

// 获取格式化的时间
private fun getFormattedTime(): String {
    val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return timeFormat.format(Calendar.getInstance().time)
}

// 获取格式化的日期
private fun getFormattedDate(): String {
    val dateFormat = SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault())
    return dateFormat.format(Calendar.getInstance().time)
}

// 获取星期几
private fun getDayOfWeek(): String {
    val dateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    return dateFormat.format(Calendar.getInstance().time)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Work1clockTheme {
        ClockScreen()
    }
}
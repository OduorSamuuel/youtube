package com.example.youtube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.youtube.ui.theme.YoutubeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            YoutubeTheme {
                YoutubeUI()
            }
        }
    }
}

@Composable
fun YoutubeUI() {
    Scaffold(
        topBar = {
            YoutubeTopAppBar()
        },
        bottomBar = {
            YoutubeBottomNavigation()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(sampleVideoData) { videoData ->
                VideoThumbnailItem(videoData)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YoutubeTopAppBar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.White
        ),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.logo2),
                        contentDescription = "YouTube Logo",
                        modifier = Modifier.size(100.dp),
                        tint = Color.Unspecified
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Cast,
                        contentDescription = "Cast",
                        modifier = Modifier.size(24.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.VideoCall,
                        contentDescription = "Create",
                        modifier = Modifier.size(24.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        modifier = Modifier.size(24.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    )
                }
            }
        }
    )
}

@Composable
fun VideoThumbnailItem(videoData: VideoData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Image(
            painter = painterResource(id = videoData.thumbnailResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = videoData.title,
                    fontWeight = FontWeight.W500,
                    fontSize = 15.sp,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${videoData.channelName} • ${if (videoData.viewCount >= 1000000) "${videoData.viewCount / 1000000}M views" else "${videoData.viewCount} views"}",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                modifier = Modifier.size(20.dp),
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun YoutubeBottomNavigation() {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        NavigationBarItem(
            icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                    Text("Home", fontSize = 10.sp)
                }
            },
            selected = true,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.Explore, contentDescription = null)
                    Text("Explore", fontSize = 10.sp)
                }
            },
            selected = false,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    Text("Create", fontSize = 10.sp)
                }
            },
            selected = false,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.Subscriptions, contentDescription = null)
                    Text("Subscriptions", fontSize = 10.sp)
                }
            },
            selected = false,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.LibraryBooks, contentDescription = null)
                    Text("Library", fontSize = 10.sp)
                }
            },
            selected = false,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.Gray
            )
        )
    }
}

data class VideoData(
    val thumbnailResId: Int,
    val title: String,
    val channelName: String,
    val viewCount: Int
)

val sampleVideoData = listOf(
    VideoData(
        thumbnailResId = R.drawable.thumbnail,
        title = "The Beauty of Existence - Heart Touching",
        channelName = "Nasheeed",
        viewCount = 19210251
    ),
    VideoData(
        thumbnailResId = R.drawable.minecraft,
        title = "Minecraft | Movie Trailer | Mine craft movie",
        channelName = "DIY Toys",
        viewCount = 24000000
    ),
    VideoData(
        thumbnailResId = R.drawable.thumbnail,
        title = "DIY Toys | Satisfying And Relaxing | DIY TikTok Compilation",
        channelName = "DIY Toys",
        viewCount = 24000000
    ),
    VideoData(
        thumbnailResId = R.drawable.thumbnail,
        title = "The Beauty of Existence - Heart Touching",
        channelName = "Nasheeed",
        viewCount = 19210251
    ),
    VideoData(
        thumbnailResId = R.drawable.thumbnail,
        title = "DIY Toys | Satisfying And Relaxing | DIY TikTok Compilation",
        channelName = "DIY Toys",
        viewCount = 24000000
    ),
    VideoData(
        thumbnailResId = R.drawable.oil,
        title = "Saudi Arabia - The Making of a Financial Empire | A Documentary",
        channelName = "Finaus",
        viewCount = 24000000
    )
)
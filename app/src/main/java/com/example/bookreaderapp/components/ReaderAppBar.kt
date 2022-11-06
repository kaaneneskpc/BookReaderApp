package com.example.bookreaderapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bookreaderapp.navigation.ReaderBookScreens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ReaderAppBar(title: String, icon: ImageVector? = null, showProfile: Boolean = true, navController: NavHostController, onBackArrowClicked:() -> Unit = {}) {

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if(showProfile) {
                    Image(imageVector = Icons.Default.Person, contentDescription = "", modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .scale(0.9f)
                        .clickable {
                            navController.navigate(ReaderBookScreens.StatsScreen.name)
                        })
                }

                if(icon != null) {
                    Icon(imageVector = icon, contentDescription = "Arrow Back", tint = Color.Red.copy(alpha = 0.7f), modifier = Modifier.clickable {
                        onBackArrowClicked.invoke()
                    })
                }

                Spacer(modifier = Modifier.width(20.dp))
                Text(text = title, color = Color.Red.copy(alpha = 0.7f), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
                Spacer(modifier = Modifier.width(120.dp))

            }
        },
        actions = {
            if(showProfile) {
                IconButton(onClick = {
                    FirebaseAuth.getInstance().signOut().run {
                        navController.navigate(ReaderBookScreens.LoginScreen.name)
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Logout, contentDescription = "Logout")
                }
            } },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )

}
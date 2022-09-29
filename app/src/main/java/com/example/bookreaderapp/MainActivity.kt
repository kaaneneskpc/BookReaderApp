package com.example.bookreaderapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookreaderapp.navigation.ReaderBookNavigation
import com.example.bookreaderapp.ui.theme.BookReaderAppTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookReaderAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ReaderBookApp()
                }
            }
        }
    }

    companion object {
        const val CREATE_ACCOUNT = "Create Account"
        const val LOGIN = "Login"
        const val SIGN_UP = "Sign Up"
        const val NEW_USER = "New User?"
        const val CREATE_ACCOUNT_INFO = "Please enter a valid email and password that is at least 6 characters"
        const val READER_BOOK = "Reader Book"
        const val YOUR_READING = "Your reading \n " + "activity right now"
        const val READING_LIST = "Reading List"
    }
}

@Composable
fun ReaderBookApp() {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        ReaderBookNavigation()
    }
}


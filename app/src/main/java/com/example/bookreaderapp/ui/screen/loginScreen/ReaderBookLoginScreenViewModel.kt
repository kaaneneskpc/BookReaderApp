package com.example.bookreaderapp.ui.screen.loginScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreaderapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ReaderBookLoginScreenViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: MutableStateFlow<Boolean> = _loading

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Log.d("FB", "signInWithEmailAndPassword: Yayayay! ${task.result}")
                        home()
                    }
                    else {
                        Log.d("FB", "signInWithEmailAndPassword: ${task.result}")
                    }
                }

        }catch (ex: Exception){
            Log.d("FB", "signInWithEmailAndPassword: ${ex.message}")
        }
    }

    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit) {
        if (!_loading.value) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result?.user?.email?.split('@')?.get(0)
                        createUser(displayName)
                        home()
                    }else {
                        Log.d("FB", "createUserWithEmailAndPassword: ${task.result.toString()}")

                    }
                    _loading.value = false
                }
           }
    }

   private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid

        val user = User(userId = userId.toString(),
           displayName = displayName.toString(),
           avatarUrl = "",
           quote = "Life is great",
           profession = "Android Developer",
           id = null).toMap()

        FirebaseFirestore.getInstance().collection("users").add(user)

    }



}
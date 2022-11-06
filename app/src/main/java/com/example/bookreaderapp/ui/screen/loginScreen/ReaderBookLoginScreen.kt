package com.example.bookreaderapp.ui.screen.loginScreen
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookreaderapp.components.*
import com.example.bookreaderapp.navigation.ReaderBookScreens
import com.example.bookreaderapp.utils.Constants.CREATE_ACCOUNT
import com.example.bookreaderapp.utils.Constants.CREATE_ACCOUNT_INFO
import com.example.bookreaderapp.utils.Constants.LOGIN
import com.example.bookreaderapp.utils.Constants.NEW_USER
import com.example.bookreaderapp.utils.Constants.SIGN_UP


@Composable
fun ReaderBookLoginScreen(navController: NavHostController, loginScreenViewModel: ReaderBookLoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val showLoginForm = rememberSaveable { mutableStateOf(true) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ReaderLogo()
            if (showLoginForm.value) UserForm(loading = false, isCreateAccount = false) { email, password ->
                loginScreenViewModel.signInWithEmailAndPassword(email, password) {
                    navController.navigate(ReaderBookScreens.HomeScreen.name)
                }
            } else {
                UserForm(loading = false, isCreateAccount = true) { email, password ->
                    loginScreenViewModel.createUserWithEmailAndPassword(email, password) {
                        navController.navigate(ReaderBookScreens.HomeScreen.name)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val text = if (showLoginForm.value) SIGN_UP else LOGIN
            Text(text = NEW_USER)
            Text(text,
                modifier = Modifier
                    .clickable {
                        showLoginForm.value = !showLoginForm.value
                    }
                    .padding(start = 5.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondaryVariant)

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(loading: Boolean = false, isCreateAccount: Boolean = false, onDone: (String, String) -> Unit = { email, pass -> }) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val  passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    val modifier = Modifier
        .height(350.dp)
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState())

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        if (isCreateAccount) Text(text = CREATE_ACCOUNT_INFO,
            modifier = Modifier.padding(4.dp)) else Text("")
        EmailInput(emailState = email, enabled = !loading, onAction = KeyboardActions {
            passwordFocusRequest.requestFocus()
        })
        PasswordInput(
            modifier = Modifier.focusRequester(passwordFocusRequest),
            passwordState = password,
            labelId = "Password",
            enabled = true,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(email.value.trim(), password.value.trim())
            })
        SubmitButton(
            textId = if (isCreateAccount) CREATE_ACCOUNT else LOGIN,
            loading = loading,
            validInputs = valid
        ){
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}







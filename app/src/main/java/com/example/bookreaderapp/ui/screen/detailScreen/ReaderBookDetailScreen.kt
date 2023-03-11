package com.example.bookreaderapp.ui.screen.detailScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.bookreaderapp.components.ReaderAppBar
import com.example.bookreaderapp.data.Resource
import com.example.bookreaderapp.model.Item
import com.example.bookreaderapp.navigation.ReaderBookScreens


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ReaderBookDetailScreen(
    navController: NavController,
    bookId: String,
    detailScreenViewModel: ReaderDetailScreenViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            ReaderAppBar(
                title = "Book Details",
                icon = Icons.Default.ArrowBack,
                showProfile = false,
                navController = navController as NavHostController
            ) {
                navController.navigate(ReaderBookScreens.SearchScreen.name)
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(top = 12.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val bookInfo = produceState<Resource<Item>>(initialValue = Resource.Loading()) {
                    value = detailScreenViewModel.getBookInfo(bookId)
                }.value

                if(bookInfo.data == null){
                    Row {
                        LinearProgressIndicator()
                    }
                } else {
                    ShowBookDetails(bookInfo, navController)
                }

            }
        }
    }
}


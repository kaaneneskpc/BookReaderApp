package com.example.bookreaderapp.ui.screen.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.bookreaderapp.components.BookRating
import com.example.bookreaderapp.components.ReaderAppBar
import com.example.bookreaderapp.components.RoundedButton
import com.example.bookreaderapp.components.TitleSection
import com.example.bookreaderapp.model.Book
import com.example.bookreaderapp.navigation.ReaderBookScreens
import com.example.bookreaderapp.utils.Constants.READER_BOOK
import com.example.bookreaderapp.utils.Constants.READING_LIST
import com.example.bookreaderapp.utils.Constants.YOUR_READING

@Composable
fun ReaderBookHomeScreen(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(topBar = {
        ReaderAppBar(title = READER_BOOK, navController = navController as NavHostController)
    }, floatingActionButton = {
        FabContent{ navController.navigate(ReaderBookScreens.SearchScreen.name) }
    }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeContent(navController = navController)
        }
    }
}

@Composable
fun HomeContent(navController: NavController) {

    val listOfBooks = listOf(
        Book(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null),
        Book(id = "dadfa", title = " Again", authors = "All of us", notes = null),
        Book(id = "dadfa", title = "Hello ", authors = "The world us", notes = null),
        Book(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null),
        Book(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null))
    Column(modifier = Modifier.padding(2.dp), verticalArrangement = Arrangement.Top) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = YOUR_READING)
        }
       ReadingRightNowArea(listOfBooks = listOf(), navController =  navController)
       TitleSection(label = READING_LIST)
        BookListArea(listOfBooks = listOfBooks, navController = navController)
    }
}

@Composable
fun ListCard(book: Book = Book("1","Running", "Me And You", "Hello New Reader"),
        onPressDetails: (String) -> Unit = {}) {

    val context = LocalContext.current
    val resources = context.resources
    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spacing = 10.dp

    Card(shape = RoundedCornerShape(29.dp),
        backgroundColor = Color.White,
        elevation = 6.dp,
        modifier = Modifier
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp)
            .clickable { onPressDetails.invoke(book.title.toString()) }) {

        Column(
            modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.Center) {

                Image(
                    painter = rememberImagePainter(data = ""),
                    contentDescription = "book image",
                    modifier = Modifier
                        .height(140.dp)
                        .width(100.dp)
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.width(50.dp))

                Column(
                    modifier = Modifier.padding(top = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Rounded.FavoriteBorder,
                        contentDescription = "Fav Icon",
                        modifier = Modifier.padding(bottom = 1.dp)
                    )

                    BookRating(score = 3.5)
                }

            }
            Text(
                text = book.title.toString(), modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = book.authors.toString(), modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.caption
            )
        }

       /* val isStartedReading = remember {
            mutableStateOf(false)
        } */

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            // isStartedReading.value = book.startedReading != null
            RoundedButton(
                label = "Reading", // if (isStartedReading.value) "Reading" else "Not Yet",
                radius = 70
            )

        }
    }
}

@Composable
fun ReadingRightNowArea(listOfBooks: List<Book>,
                        navController: NavController) {

    ListCard()
   /*
    val readingNowList = listOfBooks.filter { mBook ->
        mBook.startedReading != null && mBook.finishedReading == null
    }

    HorizontalScrollableComponent(readingNowList){
        Log.d("TAG", "BoolListArea: $it")
        navController.navigate(ReaderScreens.UpdateScreen.name + "/$it")
    } */

}

 @Composable
fun BookListArea(listOfBooks: List<Book>,
                 navController: NavController) {
    /* val addedBooks = listOfBooks.filter { mBook ->
        mBook.startedReading == null && mBook.finishedReading == null
    } */


    HorizontalScrollableComponent(listOfBooks) {
        //TODO: on card Clicked navigate detail
    }
/* {
        navController.navigate(ReaderScreens.UpdateScreen.name +"/$it")

    } */
}

@Composable
fun HorizontalScrollableComponent(listOfBooks: List<Book>,
                                  // viewModel: ReaderBookHomeScreenViewModel = hiltViewModel(),
                                  onCardPressed: (String) -> Unit) {
     val scrollState = rememberScrollState()

    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(280.dp)
        .horizontalScroll(scrollState)) {
        /* if (viewModel.data.value.loading == true) {
            LinearProgressIndicator()

        } else {
            if (listOfBooks.isNullOrEmpty()){
                Surface(modifier = Modifier.padding(23.dp)) {
                    Text(text = "No books found. Add a Book",
                        style = TextStyle(
                            color = Color.Red.copy(alpha = 0.4f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    )

                }
            }  */
                for (book in listOfBooks) {
                    ListCard(book) {
                        onCardPressed(it)

                    }
                }
       }
}


 

@Composable
fun FabContent(onTap: (String) -> Unit) {
    FloatingActionButton(onClick = { onTap("") },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color.Magenta) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add a Book", tint = MaterialTheme.colors.onSecondary)
    }
}

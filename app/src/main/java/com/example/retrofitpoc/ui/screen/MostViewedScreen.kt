package com.example.retrofitpoc.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.retrofitpoc.R
import com.example.retrofitpoc.model.Articles
import com.example.retrofitpoc.model.Media
import com.example.retrofitpoc.model.MediaMetaData
import com.example.retrofitpoc.ui.mocks.articlesMock
import com.example.retrofitpoc.ui.viewmodels.MostViewedViewModel
import kotlin.math.max

@Composable
fun MostViewedScreen(
    modifier: Modifier,
    viewModel: MostViewedViewModel = viewModel()
) {
    val articles by viewModel.articles.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) { //chama a função fetchArticles em uma corrotina
        viewModel.fetchArticles()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 2.dp, horizontal = 10.dp)
    ){
        NYTTopBar()
        when {
            isLoading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }

            error != null -> Text(text = error!!, color = MaterialTheme.colorScheme.error)
            articles != null -> {
                ListOfArticles(
                    modifier = Modifier,
                    listOfArticles = articles!!.results
                )
            }
        }
    }
}

@Composable
fun ListOfArticles(
    modifier: Modifier,
    listOfArticles: List<Articles>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(listOfArticles) { article ->
            ArticleCard(
                modifier = Modifier,
                articles = article
            )
            Spacer(modifier = Modifier.heightIn(10.dp))
        }
    }
}

@Composable
fun ArticleCard(
    modifier: Modifier,
    articles: Articles
) {
    val mediaIsNotEmpty = articles.media.isNotEmpty()
    val media: Media? = if(mediaIsNotEmpty) articles.media[0] else null
    val mediaMetaData: MediaMetaData? = media?.mediametadata?.get(2)

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(0.5.dp, Color.Black.copy(0.2f)),
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            if (mediaMetaData != null) {
                AsyncImage(
                    model = mediaMetaData.url,
                    contentDescription = mediaMetaData.format,
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                modifier = Modifier
                    .padding(4.dp),
            ) {
                Text(
                    text = articles.title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp
                )
                Text(
                    text = articles.byline,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = articles.updated, fontSize = 10.sp, maxLines = 1)
            }
        }

    }
}

@Composable
fun NYTTopBar(){
    Row(
        modifier = Modifier
            .fillMaxHeight(0.12f)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.the_new_york_times_logo),
            contentDescription = "The NYT logo",
            contentScale = ContentScale.Crop
        )
    }
}
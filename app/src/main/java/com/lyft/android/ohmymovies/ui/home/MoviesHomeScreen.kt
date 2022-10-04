@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.lyft.android.ohmymovies.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lyft.android.ohmymovies.data.repository.models.MovieModel
import com.lyft.android.ohmymovies.data.repository.models.MoviesSection
import com.lyft.android.ohmymovies.data.repository.models.posterFullUrl
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.rememberDrawablePainter

@Composable
fun MoviesHomeScreen(
    viewModel: MoviesHomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.moviesHomeStateFlow.collectAsStateWithLifecycle()

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            SwipeRefresh(
                modifier = Modifier.padding(it),
                state = rememberSwipeRefreshState(isRefreshing = uiState.isLoading),
                onRefresh = viewModel::syncMovies
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    uiState.sections.forEach { section ->
                        MoviesSection(section)
                    }
                }
            }
        }
    )

    uiState.errorMessage?.let { message ->
        val snackbarText = message.asString()
        LaunchedEffect(scaffoldState, viewModel, message, snackbarText) {
            scaffoldState.snackbarHostState.showSnackbar(snackbarText)
            viewModel.onErrorShown()
        }
    }
}

@Composable
private fun MoviesSection(movieSection: MoviesSection) {
    Spacer(modifier = Modifier.height(8.dp))

    Text(
        modifier = Modifier.padding(start = 8.dp),
        text = movieSection.title.asString(),
        style = MaterialTheme.typography.h4
    )

    Spacer(modifier = Modifier.height(4.dp))

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(movieSection.movies, key = { it.id }) { movie ->
            MovieItem(movie)
        }
    }
}

@Composable
fun MovieItem(movie: MovieModel) {
    Surface(
        modifier = Modifier
            .height(200.dp)
            .width(160.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            MoviePoster(movie)

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.body1.copy(color = Color.White, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = movie.rating.toString(),
                    style = MaterialTheme.typography.body2.copy(color = Color.White, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
private fun MoviePoster(movie: MovieModel) {
    GlideImage(
        requestBuilder = {
            Glide
                .with(LocalContext.current)
                .asDrawable()
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .thumbnail(Glide.with(LocalContext.current).asDrawable().sizeMultiplier(0.6f))
                .transition(withCrossFade())
        },
        contentScale = ContentScale.FillWidth,
        imageModel = movie.posterFullUrl(),
        modifier = Modifier.fillMaxSize(),
        success = {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberDrawablePainter(it.drawable),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Transparent,
                                    Color.Black
                                )
                            )
                        )
                )
            }
        }
    )
}

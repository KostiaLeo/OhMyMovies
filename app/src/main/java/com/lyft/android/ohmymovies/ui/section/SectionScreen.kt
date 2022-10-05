package com.lyft.android.ohmymovies.ui.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.lyft.android.ohmymovies.data.repository.models.MovieModel
import com.lyft.android.ohmymovies.data.repository.models.backdropFullUrl
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.rememberDrawablePainter

@Composable
fun SectionScreen(
    viewModel: SectionViewModel = hiltViewModel()
) {
    val moviesPagingItems = viewModel.moviesPagingFlow.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyPagingItems = moviesPagingItems) { movie ->
            BackdropMovieItem(movie)
        }
    }
}

@Composable
fun BackdropMovieItem(movie: MovieModel?) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            movie ?: return@Box

            MovieBackdrop(movie)

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
private fun MovieBackdrop(movie: MovieModel) {
    GlideImage(
        requestBuilder = {
            Glide
                .with(LocalContext.current)
                .asDrawable()
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .thumbnail(Glide.with(LocalContext.current).asDrawable().sizeMultiplier(0.6f))
                .transition(DrawableTransitionOptions.withCrossFade())
        },
        contentScale = ContentScale.FillWidth,
        imageModel = movie.backdropFullUrl(),
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

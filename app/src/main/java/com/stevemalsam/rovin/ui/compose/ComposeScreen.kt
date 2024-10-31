package com.stevemalsam.rovin.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.compose.SubcomposeAsyncImage
import coil3.test.FakeImage
import com.stevemalsam.rovin.R
import com.stevemalsam.rovin.network.models.Photo
import com.stevemalsam.rovin.network.models.PhotoCamera
import com.stevemalsam.rovin.network.models.Rover
import com.stevemalsam.rovin.network.models.RoverCamera
import kotlinx.datetime.LocalDate

val testCamera = PhotoCamera(1, "Mike", 1, "Michael")
val testRoverCamera = RoverCamera("Joe", "Joseph")
val testRover = Rover(
    1,
    "Greg",
    LocalDate(2024, 1, 11),
    LocalDate(2024, 2, 10),
    "active",
    1000,
    LocalDate(2024, 4, 15),
    10,
    listOf(
        testRoverCamera
    )
)

val fakePhoto1 = Photo(
    1,
    1000,
    testCamera,
    "http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631200305217E01_DXXX.jpg",
    LocalDate(2024, 1, 11),
    testRover
)
val fakePhoto2 = Photo(
    2,
    1000,
    testCamera,
    "http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631190503679E04_DXXX.jpg",
    LocalDate(2024, 1, 12),
    testRover
)

@OptIn(ExperimentalCoilApi::class)
val previewHandler = AsyncImagePreviewHandler {
    FakeImage(color = Color.Blue.toArgb())
}

class FakePhoto: PreviewParameterProvider<Photo> {
    override val values: Sequence<Photo>
        get() = sequenceOf(
                fakePhoto1, fakePhoto2
            )
}

class FakePhotoList: CollectionPreviewParameterProvider<Photo>(
    collection = listOf(fakePhoto1, fakePhoto2)
)

@Composable
fun ComposeScreen(photos: List<Photo>,
                  modifier: Modifier = Modifier,
                  contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    PhotosList(photos)
}

@Composable
fun PhotosList(photos: List<Photo>,
               modifier: Modifier = Modifier,
               contentPadding: PaddingValues = PaddingValues(0.dp)) {
                   LazyColumn(Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.spacedBy(8.dp),
                       contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
                       items(items = photos) { photo: Photo ->
                           PhotoCard(photo, Modifier.fillMaxWidth())
                       }
                   }
               }

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
fun PhotoCard(@PreviewParameter(FakePhoto::class) photo: Photo,
              modifier: Modifier = Modifier) {
    Card {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            val imageModifier = Modifier.aspectRatio(3f/2f)
            SubcomposeAsyncImage(
                model = photo.imgSrc,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                loading = { CircularProgressIndicator() }
            )
            Column (
                modifier = Modifier
                    .background(color = colorResource(R.color.halfgrey) )
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("${photo.id}",
                    style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text("${photo.earthDate}",
                    style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview
@Composable
fun ComposeScreenPreview() {
    ComposeScreen(listOf(fakePhoto1, fakePhoto2))
}
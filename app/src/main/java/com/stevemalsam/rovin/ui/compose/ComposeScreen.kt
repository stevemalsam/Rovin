package com.stevemalsam.rovin.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.stevemalsam.rovin.models.Photo
import com.stevemalsam.rovin.models.PhotoCamera
import com.stevemalsam.rovin.models.Rover
import com.stevemalsam.rovin.models.RoverCamera
import kotlinx.datetime.LocalDate

val testCamera = PhotoCamera(1, "Mike", 1, "Michael")
val testRoverCamera = RoverCamera("Joe", "Joseph")
val testRover = Rover(1, "Greg", LocalDate(2024, 1, 11), LocalDate(2024, 2, 10), "active", 1000, LocalDate(2024, 4, 15), 10, listOf(
    testRoverCamera))

val fakePhoto1 = Photo(1, 1000, testCamera, "http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631200305217E01_DXXX.jpg", LocalDate(2024, 1, 11), testRover)
val fakePhoto2 = Photo(2, 1000, testCamera, "http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631190503679E04_DXXX.jpg", LocalDate(2024, 1, 12), testRover)

class FakePhoto: PreviewParameterProvider<Photo> {
    override val values: Sequence<Photo>
        get() = sequenceOf(
                fakePhoto1, fakePhoto2
            )
}

class FakePhotoList: CollectionPreviewParameterProvider<Photo>(
    collection = listOf(fakePhoto1, fakePhoto2)
)

@Preview
@Composable
fun ComposeScreen(@PreviewParameter(FakePhotoList::class) photos: List<Photo>,
                  modifier: Modifier = Modifier,
                  contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    PhotosList(photos)
}

@Composable
fun PhotosList(photos: List<Photo>,
               modifier: Modifier = Modifier,
               contentPadding: PaddingValues = PaddingValues(0.dp)) {
                   LazyColumn(Modifier.fillMaxSize()) {
                       items(photos) {
                           PhotoCard(it, Modifier.fillMaxWidth())
                       }
                   }
               }

@Preview
@Composable
fun PhotoCard(@PreviewParameter(FakePhoto::class) photo: Photo,
              modifier: Modifier = Modifier) {
    Card {
        SubcomposeAsyncImage(
            model = photo.imgSrc,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            loading = { CircularProgressIndicator() }
        )
    }
}


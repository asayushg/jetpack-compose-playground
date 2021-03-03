package ayush.pawfect.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ayush.pawfect.R
import ayush.pawfect.model.Dog
import ayush.pawfect.util.loadPicture

@Composable
fun ViewDog(
    dog: Dog
) {

    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .fillMaxSize(),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            Column {

                val image = loadPicture(
                    url = dog.url,
                    defaultImage = R.drawable.dog
                )
                image.value?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "Dog",
                    )
                }
            }

            dog.breeds[0].name?.let { title ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 2.dp, start = 8.dp, end = 8.dp),
                ) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h3
                    )
                    dog.breeds[0].origin?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.Start),
                            style = MaterialTheme.typography.h4
                        )
                    }
                }
            }


            dog.breeds[0].temperament?.let { list ->

                val result: List<String> = list.split(",").map { it.trim() }

                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    result.forEach {
                        Chip(value = it)
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    dog.breeds[0].height.let {
                        CardView(head = "Height", value = it.imperial)
                    }

                    dog.breeds[0].weight.let {
                        CardView(head = "Weight", value = it.imperial)
                    }

                    dog.breeds[0].life_span?.let {
                        CardView(head = "Life Span", value = it)
                    }
                }
            }


            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                dog.breeds[0].bred_for?.let {
                    CardView(head = "Bred For", value = it)
                }
                dog.breeds[0].breed_group?.let {
                    CardView(head = "Breed Group", value = it)
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(
                    text = "About Me",
                    style = MaterialTheme.typography.h3
                )
                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam eu est tellus. Cras sem tellus, facilisis quis mattis eu, consectetur vitae nulla. Nam eu suscipit tellus, in pretium elit. Curabitur sed tristique ex, vel auctor eros. Cras tincidunt nunc et risus consequat, id consectetur nisi dignissim. Pellentesque volutpat non massa id pulvinar. Praesent consequat tempus magna, tempor malesuada purus viverra at. Fusce quis hendrerit dui, a bibendum quam.",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify
                )
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Button(onClick = { }) {
                    Text(
                        text = "Adopt Me",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.button,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}



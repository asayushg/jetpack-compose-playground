package ayush.pawfect.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ayush.pawfect.R


@Composable
fun SearchAppBar(
    query: String,
    onQueryValueChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    onToggleTheme: () -> Unit,
    darkMode: Boolean,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.secondary,
        elevation = 8.dp
    ) {

        Column {

            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp),
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                    value = query,
                    onValueChange = {
                        onQueryValueChanged(it)
                    },
                    label = {
                        Text(text = "Breed Information")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onExecuteSearch()
                        }
                    ),
                    leadingIcon = {
                        Icon(Icons.Filled.Search, null)
                    },
                )

                IconButton(
                    onClick = onToggleTheme,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 4.dp),
                ) {
                    if (darkMode)
                        Icon(
                            painter = painterResource(id = R.drawable.ic_light_mode),
                            contentDescription = "Light Mode",
                        )
                    else
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dark_mode),
                            contentDescription = "Dark Mode"
                        )
                }
            }
        }
    }

}
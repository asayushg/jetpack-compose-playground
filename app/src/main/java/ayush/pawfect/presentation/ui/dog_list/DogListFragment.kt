package ayush.pawfect.presentation.ui.dog_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import ayush.pawfect.presentation.BaseApplication
import ayush.pawfect.presentation.components.DogCard
import ayush.pawfect.presentation.components.SearchAppBar
import ayush.pawfect.presentation.theme.AppTheme
import ayush.pawfect.util.Constants.PAGE_SIZE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DogListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication
    private val viewModel: DogListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {
                AppTheme(
                    darkTheme = application.isDarkTheme.value
                ) {

                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colors.background)
                    ) {

                        val dogs = viewModel.dogs.value
                        val searchDogs = viewModel.searchDogs.value

                        SearchAppBar(
                            query = viewModel.query.value,
                            onQueryValueChanged = viewModel::onQueryChanged,
                            onExecuteSearch = viewModel::searchDogs,
                            onToggleTheme = application::toggleLightTheme,
                            darkMode = application.isDarkTheme.value
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colors.background)
                        ) {
                            if (viewModel.loading.value && dogs.isEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .align(Alignment.Center)
                                ) {
                                    CircularProgressIndicator()
                                }
                            } else {

                                LazyColumn {
                                    itemsIndexed(
                                        items = dogs
                                    ) { index, dog ->
                                        viewModel.onChangeListScrollPosition(index)
                                        if ((index + 1) >= ((viewModel.page.value +1)* PAGE_SIZE) &&
                                            !viewModel.loading.value
                                        ) {
                                            viewModel.nextPage()
                                        }
                                        DogCard(
                                            dog = dog,
                                            onClick = {
                                                val bundle = bundleOf("Id" to dog.id)
                                                view?.findNavController()
                                                    ?.navigate(ayush.pawfect.R.id.showDog, bundle)
                                            }
                                        )

                                        if (viewModel.loading.value && dogs.isNotEmpty()
                                            && index == dogs.size - 1
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(8.dp),
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                CircularProgressIndicator()
                                            }
                                        }
                                    }
                                }
                                LazyColumn {
                                    itemsIndexed(
                                        items = searchDogs
                                    ) { _, dog ->
                                        DogCard(
                                            breed = dog,
                                            onClick = {
                                                val bundle = bundleOf("Id" to dog.reference_image_id)
                                                view?.findNavController()
                                                    ?.navigate(ayush.pawfect.R.id.showDog, bundle)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
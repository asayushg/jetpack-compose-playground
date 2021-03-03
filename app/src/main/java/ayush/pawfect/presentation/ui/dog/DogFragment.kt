package ayush.pawfect.presentation.ui.dog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ayush.pawfect.presentation.BaseApplication
import ayush.pawfect.presentation.components.ViewDog
import ayush.pawfect.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DogFragment :Fragment(){

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: DogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {

                val Id = arguments?.getString("Id")
                val dog = viewModel.dog.value
                Id?.let {
                   if (viewModel.id == "") viewModel.setIdValue(it)
             }

                AppTheme(darkTheme = application.isDarkTheme.value) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colors.background),
                    ) {

                        if (viewModel.loading.value) {
                            Box(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.Center)
                            ) {
                                CircularProgressIndicator()
                            }
                        } else {
                            dog?.let {
                                ViewDog(dog = it)
                            }
                        }

                    }
                }

            }
        }
    }


}
package ayush.pawfect.presentation.ui.dog

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ayush.pawfect.model.Dog
import ayush.pawfect.repository.Repository
import kotlinx.coroutines.launch


class DogViewModel
@ViewModelInject
constructor(
    private val repository: Repository,
    private val apiKey: String,
): ViewModel(){


    val loading = mutableStateOf(false)
    val dog: MutableState<Dog?> = mutableStateOf(null)
    var id: String = ""

    private fun getDog() {

        viewModelScope.launch {
            loading.value = true
            val result = repository.getDog(
                apiKey = apiKey,
                id = id
            )
            dog.value = result
            loading.value = false
            Log.d("TAG", "getDog: ${result.toString()}")
        }

    }

    fun setIdValue(id: String){
        this.id = id
        getDog()
    }

}
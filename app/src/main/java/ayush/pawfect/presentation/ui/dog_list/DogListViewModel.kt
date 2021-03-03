package ayush.pawfect.presentation.ui.dog_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ayush.pawfect.model.Breed
import ayush.pawfect.model.Dog
import ayush.pawfect.repository.Repository
import ayush.pawfect.util.Constants.ORDER
import ayush.pawfect.util.Constants.PAGE_SIZE
import ayush.pawfect.util.Constants.SIZE
import kotlinx.coroutines.launch


class DogListViewModel
@ViewModelInject
constructor(
    private val repository: Repository,
    private val apiKey: String,
) : ViewModel() {

    val query = mutableStateOf("")
    val loading = mutableStateOf(false)
    val dogs: MutableState<List<Dog>> = mutableStateOf(listOf())
    val searchDogs: MutableState<List<Breed>> = mutableStateOf(listOf())
    val page = mutableStateOf(0)
    private var dogListScrollPosition = 0

    init {
        getDogs()
    }

    fun getDogs() {

        viewModelScope.launch {
            resetList()
            loading.value = true
            val result = repository.getDogs(
                apiKey = apiKey,
                size = SIZE,
                order = ORDER,
                page = 0,
                limit = PAGE_SIZE,
            )
            dogs.value = result
            loading.value = false
        }

    }

    fun nextPage() {
        viewModelScope.launch {
            if (dogListScrollPosition + 1 >= (PAGE_SIZE * (page.value +1))) {
                loading.value = true
                incrementPage()
                val result = repository.getDogs(
                    apiKey = apiKey,
                    size = SIZE,
                    order = ORDER,
                    page = 0,
                    limit = PAGE_SIZE,
                )
                appendDogs(result)
                loading.value = false
            }
        }
    }

    private fun appendDogs(dogs: List<Dog>) {
        val current = ArrayList(this.dogs.value)
        current.addAll(dogs)
        this.dogs.value = current
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }


    private fun resetList() {
        dogs.value = listOf()
        searchDogs.value = listOf()
        page.value = 0
        dogListScrollPosition = 0
    }

    fun searchDogs() {

        viewModelScope.launch {
            resetList()
            loading.value = true
            val result = repository.searchDogs(
                apiKey = apiKey,
                query = query.value
            )
            searchDogs.value = result
            loading.value = false
        }

    }



    /***
     * Helper Functions
     *
     */

    fun onChangeListScrollPosition(position: Int) {
        dogListScrollPosition = position
    }

    fun onQueryChanged(category: String) {
        if (category == "") getDogs()
        query.value = category
    }


}
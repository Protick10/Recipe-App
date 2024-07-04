package pro.inc.recipeapp

import   androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categoryState = mutableStateOf(RecipeState())
    val categoryState: State<RecipeState> = _categoryState


    //so this init block will be called when the MainViewModel is created and it will call the fetchCategories() function
    init {
        fetchCategories()
    }
    //in order to display anything now we need to call the fetchCategories() function
    private fun fetchCategories() {
        // Call the API service to get the categories
        // Update the state with the response

        // This is a suspend function so we need to call it from a coroutine. viewModelScope is a coroutine scope provided by the ViewModel class
        // this will allow us to run the coroutine in the context of the ViewModel scope in background thread
        viewModelScope.launch {
            try {
                val  response = recipeService.getCategories()
                _categoryState.value =_categoryState.value.copy(
                    list = response.categories,  // Update the list with the response from the API service
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )

            }
        }

    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}
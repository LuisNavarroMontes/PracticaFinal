package dadm.lnavmon.practicafinal.ui.favourites

import androidx.lifecycle.*
import dadm.lnavmon.practicafinal.data.favourites.FavouritesRepository
import dadm.lnavmon.practicafinal.ui.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val favouritesRepository: FavouritesRepository): ViewModel() {
    private val favouriteQuotations = favouritesRepository.getAllQuotations().asLiveData()
    val isDeleteAllVisible = favouriteQuotations.map() { it.isNotEmpty() }

    val favoriteQuotationsLiveData: LiveData<List<Quotation>>
        get() = favouriteQuotations


    fun deleteAllQuotations() {
        viewModelScope.launch {
            favouritesRepository.deleteAllQuotations()
        }
    }

    fun deleteQuotationAtPosition(position: Int) {
        viewModelScope.launch {
            favouriteQuotations.value?.get(position)?.let {
                favouritesRepository.deleteQuotation(it)
            }
        }
    }

}
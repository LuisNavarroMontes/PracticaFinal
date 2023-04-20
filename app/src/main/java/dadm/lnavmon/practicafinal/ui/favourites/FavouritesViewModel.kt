package dadm.lnavmon.practicafinal.ui.favourites

import androidx.lifecycle.*
import dadm.lnavmon.practicafinal.ui.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(): ViewModel() {
    private val favouriteQuotations = MutableLiveData<List<Quotation>>()
    val isDeleteAllVisible = favouriteQuotations.map() { it.isNotEmpty() }
    init {
        favouriteQuotations.value = getFavoriteQuotations()
    }

    val favoriteQuotationsLiveData: LiveData<List<Quotation>>
        get() = favouriteQuotations

    private fun getFavoriteQuotations(): List<Quotation> {
        val favoriteQuotations = mutableListOf<Quotation>()
        for (i in 0 until 20) {
            val newQuotation = if (i % 2 == 0) {
                Quotation(i.toString(), "Quotation text #$i", "Author #$i")
            } else {
                if (i == 1) {
                    Quotation(i.toString(), "Imagination is more important than knowledge.", "Albert Einstein")
                } else {
                    Quotation(i.toString(), "Quotation text #$i", "Anonymous")
                }
            }
            favoriteQuotations.add(newQuotation)
        }
        return favoriteQuotations
    }

    fun deleteAllQuotations() {
        favouriteQuotations.value = emptyList()
    }

    fun deleteQuotationAtPosition(position: Int) {
        viewModelScope.launch {
            favouriteQuotations.value?.get(position)?.let {

            }
        }
    }

}
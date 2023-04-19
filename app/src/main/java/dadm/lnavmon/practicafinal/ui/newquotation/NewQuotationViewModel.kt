package dadm.lnavmon.practicafinal.ui.newquotation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dadm.lnavmon.practicafinal.ui.domain.model.Quotation
import kotlinx.coroutines.launch

class NewQuotationViewModel : ViewModel() {

    private val _userName = MutableLiveData<String>().apply {
        value = getUserName()
    }

    val userName: LiveData<String>
        get() = _userName

    private fun getUserName(): String {
        return setOf("Alice", "Bob", "Charlie", "David", "Emma").random()
    }

    private val _quotation = MutableLiveData<Quotation>()

    val quotation: LiveData<Quotation>
        get() = _quotation

    private val _isFavoriteButtonVisible = MutableLiveData<Boolean>().apply {
        value = false
    }

    val isFavoriteButtonVisible: LiveData<Boolean>
        get() = _isFavoriteButtonVisible

    val isGettingNewQuotation = MutableLiveData<Boolean>().apply {
        value = false
    }

    val isGreetingsVisible: LiveData<Boolean> = quotation.map() { it.id.isEmpty() }

    fun getNewQuotation() {
        isGettingNewQuotation.value = true
        viewModelScope.launch {
            // Make a request to the web service to retrieve a random quotation
            val num = (0..99).random().toString()
            val newQuotation = Quotation(num, "Quotation text #$num", "Author #$num")
            // Update the quotation property with the new quotation
            _quotation.value = newQuotation
            isGettingNewQuotation.value = false
            _isFavoriteButtonVisible.value = true
        }
    }

    fun addToFavourites() {
        _isFavoriteButtonVisible.value = false
    }
}
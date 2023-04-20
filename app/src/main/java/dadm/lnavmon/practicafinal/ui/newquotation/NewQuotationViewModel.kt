package dadm.lnavmon.practicafinal.ui.newquotation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dadm.lnavmon.practicafinal.data.newquotation.NewQuotationRepositoryImpl
import dadm.lnavmon.practicafinal.ui.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import data.newquotation.NewQuotationRespository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewQuotationViewModel @Inject constructor(
    private val repository: NewQuotationRespository
) : ViewModel() {

    private val _userName = MutableLiveData<String>().apply {
        value = getUserName()
    }

    val userName: LiveData<String>
        get() = _userName

    private fun getUserName(): String {
        return setOf("Alice", "Bob", "Charlie", "David", "Emma").random()
    }

    private val _errorLiveData = MutableLiveData<Throwable?>()
    val errorLiveData: LiveData<Throwable?>
        get() = _errorLiveData

    fun resetError() {
        _errorLiveData.value = null
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
            repository.getNewQuotation().fold(
                onSuccess = { quotation ->
                    _quotation.value = quotation
                    isGettingNewQuotation.value = false
                    _isFavoriteButtonVisible.value = true
                },
                onFailure = { error ->
                    _errorLiveData.value = error
                    isGettingNewQuotation.value = false
                }
            )
        }
    }

    fun addToFavourites() {
        _isFavoriteButtonVisible.value = false
    }
}
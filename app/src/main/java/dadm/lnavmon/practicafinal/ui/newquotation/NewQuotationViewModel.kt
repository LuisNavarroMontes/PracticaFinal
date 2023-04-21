package dadm.lnavmon.practicafinal.ui.newquotation

import androidx.lifecycle.*
import dadm.lnavmon.practicafinal.data.newquotation.NewQuotationManager
import dadm.lnavmon.practicafinal.data.newquotation.NewQuotationRepositoryImpl
import dadm.lnavmon.practicafinal.data.settings.SettingsRepository
import dadm.lnavmon.practicafinal.ui.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import data.newquotation.NewQuotationRespository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewQuotationViewModel @Inject constructor(
    private val manager: NewQuotationManager,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

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

    val userName: LiveData<String> = settingsRepository.getUsername().asLiveData()

    fun getNewQuotation() {
        isGettingNewQuotation.value = true
        viewModelScope.launch {
            manager.getNewQuotation().fold(
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
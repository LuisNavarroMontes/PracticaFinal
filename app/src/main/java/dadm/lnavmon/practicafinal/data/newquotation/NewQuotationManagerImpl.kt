package dadm.lnavmon.practicafinal.data.newquotation

import dadm.lnavmon.practicafinal.data.settings.SettingsRepository
import dadm.lnavmon.practicafinal.ui.domain.model.Quotation
import data.newquotation.NewQuotationRespository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewQuotationManagerImpl
@Inject constructor(private val settingsRepository : SettingsRepository,
                    private val newQuotationRepository : NewQuotationRespository
) : NewQuotationManager {

    private lateinit var language: String

    init {

        CoroutineScope(SupervisorJob()).launch {
            settingsRepository.getLanguage().collect { languageCode ->
                language = languageCode
            }
        }
        while ((!::language.isInitialized)){}
    }


    override suspend fun getNewQuotation(): Result<Quotation> {
        return newQuotationRepository.getNewQuotation(language)
    }


}
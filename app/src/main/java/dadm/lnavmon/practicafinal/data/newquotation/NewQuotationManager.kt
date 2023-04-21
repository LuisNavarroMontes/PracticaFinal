package dadm.lnavmon.practicafinal.data.newquotation

import dadm.lnavmon.practicafinal.ui.domain.model.Quotation

interface NewQuotationManager {
    suspend fun getNewQuotation(): Result<Quotation>
}
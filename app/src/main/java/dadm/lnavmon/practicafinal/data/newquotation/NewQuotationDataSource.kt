package dadm.lnavmon.practicafinal.data.newquotation

import dadm.lnavmon.practicafinal.data.newquotation.model.QuotationDto
import dadm.lnavmon.practicafinal.ui.domain.model.Quotation
import retrofit2.Response

interface NewQuotationDataSource {
    suspend fun getQuotation(language:String): Response<QuotationDto>
}
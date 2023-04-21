package data.newquotation

import dadm.lnavmon.practicafinal.ui.domain.model.Quotation


interface NewQuotationRespository {
    suspend fun getNewQuotation(language:String): Result<Quotation>

}
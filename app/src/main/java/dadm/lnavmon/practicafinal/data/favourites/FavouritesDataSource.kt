package dadm.lnavmon.practicafinal.data.favourites

import dadm.lnavmon.practicafinal.data.favourites.model.QuotationDto
import kotlinx.coroutines.flow.Flow

interface FavouritesDataSource {
    fun getAllQuotations(): Flow<List<QuotationDto>>
    fun getQuotationById(id: String): Flow<QuotationDto?>
    suspend fun addQuotation(quotation: QuotationDto)
    suspend fun deleteQuotation(quotation: QuotationDto)
    suspend fun deleteAllQuotations()
}
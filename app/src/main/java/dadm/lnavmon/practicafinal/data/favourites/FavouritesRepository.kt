package dadm.lnavmon.practicafinal.data.favourites

import dadm.lnavmon.practicafinal.ui.domain.model.Quotation
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    fun getAllQuotations(): Flow<List<Quotation>>
    fun getQuotationById(id: String): Flow<Quotation?>
    suspend fun addQuotation(quotation: Quotation)
    suspend fun deleteQuotation(quotation: Quotation)
    suspend fun deleteAllQuotations()
}
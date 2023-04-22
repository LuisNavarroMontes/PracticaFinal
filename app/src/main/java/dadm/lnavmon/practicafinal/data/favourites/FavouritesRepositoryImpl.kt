package dadm.lnavmon.practicafinal.data.favourites

import dadm.lnavmon.practicafinal.data.favourites.model.QuotationDto
import dadm.lnavmon.practicafinal.data.favourites.model.toDto
import dadm.lnavmon.practicafinal.data.favourites.model.toDomain
import dadm.lnavmon.practicafinal.ui.domain.model.Quotation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(
    private val dataSource: FavouritesDataSource
) : FavouritesRepository {
    override suspend fun addQuotation(quotation: Quotation) {
        dataSource.addQuotation(quotation.toDto())
    }

    override suspend fun deleteQuotation(quotation: Quotation) {
        dataSource.deleteQuotation(quotation.toDto())
    }

    override fun getAllQuotations(): Flow<List<Quotation>> {
        return dataSource.getAllQuotations().map { list : List<QuotationDto> ->
            list.map { itemDto : QuotationDto -> itemDto.toDomain() }
        }
    }

    override fun getQuotationById(id: String): Flow<Quotation?> {
        return dataSource.getQuotationById(id).map {
            it?.toDomain()
        }
    }

    override suspend fun deleteAllQuotations() {
        dataSource.deleteAllQuotations()
    }
}
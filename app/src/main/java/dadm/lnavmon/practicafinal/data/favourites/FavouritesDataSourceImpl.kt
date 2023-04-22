package dadm.lnavmon.practicafinal.data.favourites

import dadm.lnavmon.practicafinal.data.favourites.model.QuotationDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouritesDataSourceImpl @Inject constructor(
    private val favouritesDao: FavouritesDao
) : FavouritesDataSource {

    override fun getAllQuotations(): Flow<List<QuotationDto>> =
        favouritesDao.getAllQuotations().map { list ->
            list.map {it}
        }

    override fun getQuotationById(id: String): Flow<QuotationDto?> {
        return favouritesDao.getQuotationById(id).map {it}
    }

    override suspend fun addQuotation(quotation: QuotationDto){
        favouritesDao.insertQuotation(quotation)
    }

    override suspend fun deleteQuotation(quotation: QuotationDto){
        favouritesDao.deleteQuotation(quotation)
    }

    override suspend fun deleteAllQuotations(){
        favouritesDao.deleteAllQuotations()
    }

}
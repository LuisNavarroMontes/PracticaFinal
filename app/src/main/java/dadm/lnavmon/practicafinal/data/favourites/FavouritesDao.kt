package dadm.lnavmon.practicafinal.data.favourites

import androidx.room.*
import dadm.lnavmon.practicafinal.data.favourites.model.QuotationDto
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotation(quotation: QuotationDto)

    @Delete
    suspend fun deleteQuotation(quotation: QuotationDto)

    @Query("SELECT * FROM ${FavouritesContract.Quotation.TABLE_NAME}")
    fun getAllQuotations(): Flow<List<QuotationDto>>

    @Query("SELECT * FROM ${FavouritesContract.Quotation.TABLE_NAME} WHERE ${FavouritesContract.Quotation.COLUMN_ID} = :id")
    fun getQuotationById(id: String): Flow<QuotationDto?>

    @Query("DELETE FROM ${FavouritesContract.Quotation.TABLE_NAME}")
    suspend fun deleteAllQuotations()
}
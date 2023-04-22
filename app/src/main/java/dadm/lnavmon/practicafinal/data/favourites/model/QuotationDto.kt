package dadm.lnavmon.practicafinal.data.favourites.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dadm.lnavmon.practicafinal.data.favourites.FavouritesContract

@Entity(tableName = FavouritesContract.Quotation.TABLE_NAME)
data class QuotationDto(

    @PrimaryKey
    @ColumnInfo(name = FavouritesContract.Quotation.COLUMN_ID)
    val id: String,
    @ColumnInfo(name = FavouritesContract.Quotation.COLUMN_TEXT)
    val quote: String,
    @ColumnInfo(name = FavouritesContract.Quotation.COLUMN_AUTHOR)
    val author: String
)
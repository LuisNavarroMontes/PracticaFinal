package dadm.lnavmon.practicafinal.data.newquotation

import dadm.lnavmon.practicafinal.data.newquotation.model.QuotationDto
import dadm.lnavmon.practicafinal.ui.domain.model.Quotation
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

class NewQuotationDataSourceImpl @Inject constructor(retrofit: Retrofit) : NewQuotationDataSource {
    private val retrofitQuotationService = retrofit.create(NewQuotationRetrofit::class.java)

    override suspend fun getQuotation(language:String): Response<QuotationDto> {
        return try {
            retrofitQuotationService.getQuotation(language)
        } catch (e: Exception) {
            Response.error(
                400,
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }
    }
}
package dadm.lnavmon.practicafinal.data.newquotation.model

import dadm.lnavmon.practicafinal.ui.domain.model.Quotation
import retrofit2.Response
import java.io.IOException

fun QuotationDto.toDomain(): Quotation {
    return Quotation(id = quoteLink, quote = quoteText, author = quoteAuthor)
}

fun Response<QuotationDto>.toDomain() =
    if (isSuccessful) Result.success((body() as QuotationDto).toDomain())
    else Result.failure(IOException())
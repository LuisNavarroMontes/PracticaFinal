package dadm.lnavmon.practicafinal.data.favourites.model

import dadm.lnavmon.practicafinal.ui.domain.model.Quotation

fun QuotationDto.toDomain(): Quotation =
    Quotation(
        id = id,
        quote = quote,
        author = author
    )

fun Quotation.toDto(): QuotationDto =
    QuotationDto(
        id = id,
        quote = quote,
        author = author
    )
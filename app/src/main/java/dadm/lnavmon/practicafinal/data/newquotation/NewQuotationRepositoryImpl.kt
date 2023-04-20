package dadm.lnavmon.practicafinal.data.newquotation

import dadm.lnavmon.practicafinal.data.newquotation.model.toDomain
import dadm.lnavmon.practicafinal.ui.domain.model.Quotation
import dadm.lnavmon.practicafinal.utils.NoInternetException
import data.newquotation.NewQuotationRespository
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor(
    private val dataSource: NewQuotationDataSource,
    private val connectivityChecker: ConnectivityChecker
) : NewQuotationRespository {

    override suspend fun getNewQuotation(): Result<Quotation> {
        return if (connectivityChecker.isConnectionAvailable()) {
            dataSource.getQuotation(arrayOf("en", "ru", "xx").random()).toDomain()
        }else {
            Result.failure(NoInternetException())
        }
    }


}
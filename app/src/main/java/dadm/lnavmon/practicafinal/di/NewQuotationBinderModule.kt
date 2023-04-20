package dadm.lnavmon.practicafinal.di

import dadm.lnavmon.practicafinal.data.newquotation.NewQuotationDataSource
import dadm.lnavmon.practicafinal.data.newquotation.NewQuotationDataSourceImpl
import dadm.lnavmon.practicafinal.data.newquotation.NewQuotationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.newquotation.NewQuotationRespository

@Module
@InstallIn(SingletonComponent::class)
abstract class NewQuotationBinderModule {

    @Binds
    abstract fun bindNewQuotationRepository(repository: NewQuotationRepositoryImpl): NewQuotationRespository

    @Binds
    abstract fun bindNewQuotationDataSource(dataSource: NewQuotationDataSourceImpl): NewQuotationDataSource
}
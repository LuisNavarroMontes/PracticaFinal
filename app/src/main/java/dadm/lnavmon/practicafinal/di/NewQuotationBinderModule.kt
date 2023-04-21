package dadm.lnavmon.practicafinal.di

import dadm.lnavmon.practicafinal.data.newquotation.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.newquotation.NewQuotationRespository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NewQuotationBinderModule {

    @Binds
    @Singleton
    abstract fun bindNewQuotationRepository(repository: NewQuotationRepositoryImpl): NewQuotationRespository

    @Binds
    @Singleton
    abstract fun bindNewQuotationDataSource(dataSource: NewQuotationDataSourceImpl): NewQuotationDataSource

    @Binds
    @Singleton
    abstract fun provideNewQuotationManager(newQuotationManagerImpl: NewQuotationManagerImpl): NewQuotationManager

}
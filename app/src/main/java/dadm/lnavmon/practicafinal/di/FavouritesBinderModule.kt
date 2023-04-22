package dadm.lnavmon.practicafinal.di

import dadm.lnavmon.practicafinal.data.favourites.FavouritesDataSource
import dadm.lnavmon.practicafinal.data.favourites.FavouritesDataSourceImpl
import dadm.lnavmon.practicafinal.data.favourites.FavouritesRepository
import dadm.lnavmon.practicafinal.data.favourites.FavouritesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavouritesBinderModule {
    @Binds
    @Singleton
    abstract fun bindFavouritesDataSource(
        dataSourceImpl: FavouritesDataSourceImpl
    ): FavouritesDataSource

    @Binds
    @Singleton
    abstract fun bindFavouritesRepository(
        repositoryImpl: FavouritesRepositoryImpl
    ): FavouritesRepository
}
package dadm.lnavmon.practicafinal.di

import dadm.lnavmon.practicafinal.data.settings.SettingsDataSource
import dadm.lnavmon.practicafinal.data.settings.SettingsDataSourceImpl
import dadm.lnavmon.practicafinal.data.settings.SettingsRepository
import dadm.lnavmon.practicafinal.data.settings.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsBinderModule {

    @Binds
    @Singleton
    abstract fun bindSettingsDataSource(dataSource: SettingsDataSourceImpl): SettingsDataSource

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(repository: SettingsRepositoryImpl): SettingsRepository

}
package dadm.lnavmon.practicafinal.data.settings

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataSource: SettingsDataSource
): SettingsRepository {

    override fun getUsername(): Flow<String> {
        return settingsDataSource.getUsername()
    }
    override fun getLanguage(): Flow<String> {
        return settingsDataSource.getLanguage()
    }
}
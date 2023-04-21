package dadm.lnavmon.practicafinal.data.settings

import android.content.SharedPreferences
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): SettingsDataSource {

    private fun getUsernamePreference() = sharedPreferences.getString(KEY_USERNAME, "") ?: ""
    private fun getLanguagePreference() = sharedPreferences.getString(KEY_LANGUAGE, "")?.also {
        Log.d("SettingsDataSourceImpl", "getLanguagePreference(): $it")
    } ?: ""
    override fun getUsername(): Flow<String> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            launch(Dispatchers.IO) {
                if (KEY_USERNAME == key) {
                    trySend(getUsernamePreference())
                }
            }
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        trySend(getUsernamePreference())
        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }.flowOn(Dispatchers.IO)

    override fun getLanguage(): Flow<String> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            launch(Dispatchers.IO) {
                if (KEY_LANGUAGE == key) {
                    trySend(getLanguagePreference())
                }
            }
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        trySend(getLanguagePreference())
        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }.flowOn(Dispatchers.IO)


    companion object {
        const val KEY_USERNAME = "username"
        const val KEY_LANGUAGE = "language"
    }
}
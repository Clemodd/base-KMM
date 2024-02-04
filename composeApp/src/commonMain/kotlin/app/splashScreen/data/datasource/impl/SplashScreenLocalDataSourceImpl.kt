package app.splashScreen.data.datasource.impl

import app.splashScreen.data.datasource.SplashScreenLocalDataSource
import domain.constante.ETAPE_INSCRIPTION
import domain.constante.MY_USER_TOKEN
import kotlinx.serialization.json.Json
import specificitePlateforme.StockageEnLocalRepository
import specificitePlateforme.StringSettingConfig

class SplashScreenLocalDataSourceImpl(
    private val stockageEnLocalRepository: StockageEnLocalRepository
) : SplashScreenLocalDataSource {

    private fun getSetting(key: String, defaultValue: String): String {
        val setting = StringSettingConfig(
            stockageEnLocalRepository.getSettings(),
            key,
            defaultValue
        ).get()

        return if (setting.isBlank()) {
            defaultValue
        } else {
            Json.decodeFromString(setting)
        }
    }

    override suspend fun getToken(): String {
        return getSetting(MY_USER_TOKEN, "")
    }

    override suspend fun getEtape(): String {
        return getSetting(ETAPE_INSCRIPTION, "")
    }
}
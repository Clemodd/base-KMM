package app.splashScreen.data.datasource

interface SplashScreenLocalDataSource {
    suspend fun getToken(): String
    suspend fun getEtape(): String
}
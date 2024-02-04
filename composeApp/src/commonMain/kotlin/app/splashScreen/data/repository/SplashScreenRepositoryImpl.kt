package app.splashScreen.data.repository

import app.splashScreen.data.datasource.SplashScreenLocalDataSource
import app.splashScreen.domain.SplashScreenRepository
import commun.utils.mapper.toEtapeInscriptionEnum

class SplashScreenRepositoryImpl(
    private val localDataSource: SplashScreenLocalDataSource
) : SplashScreenRepository {
    override suspend fun getToken() = localDataSource.getToken()

    override suspend fun getEtape() = localDataSource.getEtape().toEtapeInscriptionEnum()
}

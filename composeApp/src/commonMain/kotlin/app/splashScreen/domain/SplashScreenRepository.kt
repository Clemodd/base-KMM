package app.splashScreen.domain

import domain.enum.EtapeInscriptionEnum

interface SplashScreenRepository {
    suspend fun getToken(): String
    suspend fun getEtape(): EtapeInscriptionEnum
}
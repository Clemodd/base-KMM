package app.accueil.data.repository

import app.accueil.data.datasource.AccueilLocalDataSource
import app.accueil.data.datasource.AccueilRemoteDataSource
import app.accueil.domain.AccueilRepository

class AccueilRepositoryImpl(
    private val remoteDatasource: AccueilRemoteDataSource,
    private val localDataSource: AccueilLocalDataSource
) : AccueilRepository {
}
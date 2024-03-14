package di

import app.accueil.data.api.AccueilApi
import app.accueil.data.datasource.AccueilLocalDataSourceImpl
import app.accueil.data.datasource.AccueilRemoteDataSourceImpl
import app.accueil.data.repository.AccueilRepositoryImpl
import app.splashScreen.data.api.SplashScreenApi
import app.splashScreen.data.datasource.SplashScreenLocalDataSource
import app.splashScreen.data.datasource.impl.SplashScreenLocalDataSourceImpl
import app.splashScreen.data.repository.SplashScreenRepositoryImpl
import app.splashScreen.domain.SplashScreenRepository
import org.koin.core.context.startKoin
import org.koin.dsl.module
import provideSettings
import specificitePlateforme.StockageEnLocalRepository
import specificitePlateforme.StockageEnLocalRepositoryImpl
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object KoinInitializer {
    var isInitialized = false
}

/**
 *  Pour éviter que Koin soit initialisé plusieurs fois.
 */
fun initKoin() {
    KoinInitializer.isInitialized
    if (!KoinInitializer.isInitialized) {
        startKoin {
            modules(appModule)
        }
        KoinInitializer.isInitialized = true
    }
}

// Injection de dépendances (API, DataSource et Repository)
val appModule = module {
    single<StockageEnLocalRepository> { StockageEnLocalRepositoryImpl(get()) }
    single { provideSettings() }

    single { AccueilApi() }
    single { AccueilLocalDataSourceImpl() }
    single { AccueilRemoteDataSourceImpl(get()) }
    single { AccueilRepositoryImpl(get(), get()) }

    single { SplashScreenApi() }
    single<SplashScreenLocalDataSource> { SplashScreenLocalDataSourceImpl(get()) }
    single<SplashScreenRepository> { SplashScreenRepositoryImpl(get()) }
}
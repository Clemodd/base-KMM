package app.splashScreen.ui

import app.splashScreen.domain.SplashScreenRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.enum.EtapeInscriptionEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashScreenViewModel : KoinComponent, ViewModel() {

    private val splashScreenRepository: SplashScreenRepository by inject()

    val navigationEvent = MutableStateFlow<SplashScreenNavigation?>(null)

    init {
        chargerApplication()
    }

    private fun chargerApplication() {
        viewModelScope.launch {
            val token = splashScreenRepository.getToken()
            val etape = splashScreenRepository.getEtape()

            navigationEvent.value = when {
                token.isEmpty() -> SplashScreenNavigation.GoToCreationConnectionCompte
                etape == EtapeInscriptionEnum.VERIFICATION_MAIL -> SplashScreenNavigation.GoToVerificationMail
                etape == EtapeInscriptionEnum.CHOIX_COULEUR -> SplashScreenNavigation.GoToChoixCouleur
                etape == EtapeInscriptionEnum.ACCUEIL -> SplashScreenNavigation.GoToAccueil
                else -> SplashScreenNavigation.GoToCreationConnectionCompte
            }
        }
    }

    fun clearNavigationEvent() {
        navigationEvent.value = null
    }
}
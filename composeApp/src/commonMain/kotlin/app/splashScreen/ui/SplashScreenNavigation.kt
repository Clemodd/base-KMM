package app.splashScreen.ui

import decompose.RootNavigationComponent

sealed interface SplashScreenNavigation {
    data object GoToCreationConnectionCompte : SplashScreenNavigation
    data object GoToVerificationMail : SplashScreenNavigation
    data object GoToChoixCouleur : SplashScreenNavigation
    data object GoToAccueil : SplashScreenNavigation
}

fun convertSplashScreenNavigationToConfig(navigation: SplashScreenNavigation): RootNavigationComponent.Configuration {
    return when (navigation) {
        is SplashScreenNavigation.GoToAccueil -> RootNavigationComponent.Configuration.Accueil
        is SplashScreenNavigation.GoToCreationConnectionCompte -> RootNavigationComponent.Configuration.CreationConnectionCompte
        is SplashScreenNavigation.GoToVerificationMail -> RootNavigationComponent.Configuration.VerificationMail
        is SplashScreenNavigation.GoToChoixCouleur -> RootNavigationComponent.Configuration.ChoixCouleur
    }
}
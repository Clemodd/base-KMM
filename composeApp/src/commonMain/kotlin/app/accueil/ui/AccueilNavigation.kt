package app.accueil.ui

sealed interface AccueilNavigation {
    data object GoToProfil : AccueilNavigation
}
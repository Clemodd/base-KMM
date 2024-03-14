import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.accueil.ui.Accueil
import app.splashScreen.ui.SplashScreen
import app.splashScreen.ui.convertSplashScreenNavigationToConfig
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import commun.theme.Couleurs
import commun.theme.LocalCouleurPalette
import commun.theme.LocalSpacing
import commun.theme.MesCouleurs
import commun.theme.Spacing
import decompose.RootNavigationComponent
import di.initKoin

@Composable
fun CommonMainActivity(
    root: RootNavigationComponent
) {
    initKoin() // Permet d'initialiser Koin pour pouvoir utiliser les injections de dépendances

    MyApplicationTheme {
        setNavigation(root)
    }
}

@Composable
private fun setNavigation(
    root: RootNavigationComponent
) {
    val childStack by root.childStack.subscribeAsState()
    Children(
        stack = childStack,
        animation = stackAnimation(scale())
    ) { child ->
        when (val instance = child.instance) {
            is RootNavigationComponent.Child.SplashScreen -> SplashScreen(
                viewModel = instance.viewModel,
                navigateTo = { config ->
                    root.navigateTo(
                        convertSplashScreenNavigationToConfig(
                            config
                        )
                    )
                }
            )

            is RootNavigationComponent.Child.Accueil -> Accueil(instance.viewModel)
            is RootNavigationComponent.Child.CreationConnectionCompte -> TODO()
            is RootNavigationComponent.Child.VerificationMail -> TODO()
            is RootNavigationComponent.Child.ChoixCouleur -> TODO()
            is RootNavigationComponent.Child.GoBack -> root.goBack()
        }
    }
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val baseColors = Couleurs()

    val themeColors = MesCouleurs(
        fondEcran = if (darkTheme) baseColors.fondEcranDark else baseColors.fondEcranLight,
        fondEncadre = if (darkTheme) baseColors.fondEncadreDark else baseColors.fondEncadreLight,
        fondInput = if (darkTheme) baseColors.fondInputDark else baseColors.fondInputLight,
        fondSecondaire = if (darkTheme) baseColors.fondSecondaireDark else baseColors.fondSecondaireLight,
        texte = if (darkTheme) baseColors.texteDark else baseColors.texteLight,
    )

    val typography = Typography(
        button = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(14.dp),
        medium = RoundedCornerShape(19.dp),
        large = RoundedCornerShape(30.dp)
    )
    CompositionLocalProvider(
        LocalCouleurPalette provides themeColors,
        LocalSpacing provides Spacing(),
    ) {
        MaterialTheme(
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.smtersoyoglu.navigationcodelab.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun appLaunchesOnHomeScreen() {
        // Uygulama başladığında Home ekranının yüklendiğini doğrular
        composeTestRule.onNodeWithText("Home Screen").assertIsDisplayed()
    }

    @Test
    fun navigateToSecondScreen() {
        // Home ekranından Second ekrana geçişi test eder
        composeTestRule.onNodeWithText("Go To Second Screen").performClick()
        composeTestRule.onNodeWithText("Second Screen , url: www.url.com").assertIsDisplayed()
    }

    @Test
    fun navigateToLastScreenFromSecondScreen() {
        // Second ekranından Last ekrana geçişi test eder

        // Home ekranından Second ekrana geçiş yap
        composeTestRule.onNodeWithText("Go To Second Screen").performClick()
        composeTestRule.onNodeWithText("Go To Last Screen").performClick()

        // Last ekranının yüklendiğini doğrula
        composeTestRule.onNodeWithText("Last Screen").assertIsDisplayed()
    }

    @Test
    fun navigateBackToSecondScreenFromLastScreen() {
        // Last ekranından geri dönüp Second ekrana geçişi test eder

        // Home ekranından Second ve Last ekranlarına geçiş yap
        composeTestRule.onNodeWithText("Go To Second Screen").performClick()
        composeTestRule.onNodeWithText("Go To Last Screen").performClick()

        // Last ekranında geri dön
        composeTestRule.onNodeWithText("Go Back").performClick()

        // Second ekranının yüklendiğini doğrula
        composeTestRule.onNodeWithText("Second Screen , url: www.url.com").assertIsDisplayed()
    }

    /*
    @Test
    fun navigateBackToHomeScreenFromSecondScreen() {
        // Home ekranından Second ekrana geçiş yap
        composeTestRule.onNodeWithText("Go To Second Screen").performClick()

        // popBackStack ile geri dönerek Home ekranına dönmeyi test et
        composeTestRule.activityRule.scenario.onActivity {
            it.onBackPressedDispatcher.onBackPressed()
        }

        // Home ekranının yüklendiğini doğrula
        composeTestRule.onNodeWithText("Home Screen").assertIsDisplayed()
    }


     */

    @Test
    fun tabSelectionUpdatesCurrentScreen() {
        // Sekme seçimi ile ekranların güncellenmesini test eder

        // "Second" sekmesine geçiş yap
        composeTestRule.onNodeWithText("second").performClick()
        composeTestRule.onNodeWithText("Second Screen , url: example_url").assertIsDisplayed()

        // "Last" sekmesine geçiş yap
        composeTestRule.onNodeWithText("last").performClick()
        composeTestRule.onNodeWithText("Last Screen").assertIsDisplayed()

        // "Home" sekmesine geri dön
        composeTestRule.onNodeWithText("home").performClick()
        composeTestRule.onNodeWithText("Home Screen").assertIsDisplayed()
    }
}

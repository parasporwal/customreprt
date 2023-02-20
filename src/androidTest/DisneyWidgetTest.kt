package com.hotstar.tests.growth

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.hotstar.base.setup.HomeBase
import kotlinx.coroutines.FlowPreview
import org.junit.Before
import org.junit.Test

@ExperimentalComposeUiApi
@FlowPreview
@ExperimentalUnitApi
@ExperimentalFoundationApi
open class DisneyWidgetTest : HomeBase() {

    private val landingPage = page.landingPage
    private val heroLandingPage = page.heroLandingPage

    /** before test */
    @Before
    override fun before() {
        super.before()
        loginUtils.loginAndSelectProfile(
            tds?.getPhoneNumber().toString(),
            tds?.getOTP().toString()
        )
    }

    @Test
    fun verifyDisneyWidgetExists() {
        landingPage.verifyDisneyWidgetsExists()
    }

    @Test
    fun clickOnDisneyWidget() {
        landingPage.clickOnDisneyWidget("Disney")
        heroLandingPage.isHeroLandingPageMastheadPresent()
    }

    @Test
    fun clickOnPixarWidget() {
        landingPage.clickOnDisneyWidget("Pixar")
        heroLandingPage.isHeroLandingPageMastheadPresent()
    }

    @Test
    fun clickOnMarvelWidget() {
        landingPage.clickOnDisneyWidget("Marvel")
        heroLandingPage.isHeroLandingPageMastheadPresent()
    }

    @Test
    fun clickOnStarWarsWidget() {
        landingPage.clickOnDisneyWidget("Star Wars")
        heroLandingPage.isHeroLandingPageMastheadPresent()
    }

    @Test
    fun clickOnNatGeoWidget() {
        landingPage.clickOnDisneyWidget("Nat Geo")
        heroLandingPage.isHeroLandingPageMastheadPresent()
    }

    @Test
    fun clickOnStarWidget() {
        landingPage.clickOnDisneyWidget("Star")
        heroLandingPage.isHeroLandingPageMastheadPresent()
    }
}

package com.mobiquity.mobproducts

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.android.material.tabs.TabLayout
import com.mobiquity.mobproducts.domain.ProductsRepository
import com.mobiquity.mobproducts.helper.RecyclerViewMatcher
import com.mobiquity.mobproducts.helper.TestInjector
import com.mobiquity.mobproducts.mockedApi.FakeRepositoryModule
import com.mobiquity.mobproducts.presentation.ui.MainActivity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Observable
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ProductCategoriesTest {

    @MockK
    private lateinit var productUserRepository: ProductsRepository

    @get:Rule
    val testRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        TestInjector(FakeRepositoryModule()).inject()
    }

    @Test
    fun testShowingCorrectProductItems() {
        testRule.launchActivity(null)
        checkNameOnPosition(0, "Product1")
        checkNameOnPosition(1, "Product2")
    }

    @Test
    fun testOpenProductDetailOnClick() {
        testRule.launchActivity(null)

        onView(withId(R.id.product_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )

        Thread.sleep(500)
        onView(withId(R.id.product_detail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.product_detail_name)).check(matches(withText("Product1")))
    }

    @Test
    fun testFetchProductError() {
        every { productUserRepository.getProducts() } returns Observable.error(IOException())
        TestInjector(FakeRepositoryModule(productUserRepository)).inject()
        testRule.launchActivity(null)

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.error)))
    }

    @Test
    fun testProgressBarWhileLoading() {
        every { productUserRepository.getProducts() } returns Observable.create {}
        TestInjector(FakeRepositoryModule(productUserRepository)).inject()
        testRule.launchActivity(null)
        onView(withId(R.id.progress_loading)).check(matches(isDisplayed()))
    }

    @Test
    fun testChangeTabLayout() {
        testRule.launchActivity(null)
        checkNameOnPosition(0, "Product1")
        onView(withId(R.id.categories_tab)).perform(selectTabAtPosition(1))
        checkNameOnPosition(0, "Product3")
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    private fun checkNameOnPosition(position: Int, expectedName: String) {
        onView(
            withRecyclerView(R.id.product_list).atPositionOnView(
                position,
                R.id.product_name
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedName)))
    }

    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() =
                allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }
}
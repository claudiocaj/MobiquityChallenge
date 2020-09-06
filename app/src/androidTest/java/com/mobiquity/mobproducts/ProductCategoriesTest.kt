package com.mobiquity.mobproducts

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
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
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.android.material.tabs.TabLayout
import com.mobiquity.mobproducts.di.FakeRepositoryModule
import com.mobiquity.mobproducts.domain.ProductsRepository
import com.mobiquity.mobproducts.helper.RecyclerViewMatcher
import com.mobiquity.mobproducts.helper.TestInjector
import com.mobiquity.mobproducts.presentation.ui.MainActivity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Observable
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4ClassRunner::class)
class ProductCategoriesTest {

    @MockK
    private lateinit var productUserRepository: ProductsRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testShowingCorrectProductItems() {
        TestInjector(FakeRepositoryModule()).inject()
        val activityScenario: ActivityScenario<MainActivity> =
            ActivityScenario.launch(MainActivity::class.java)

        checkNameOnPosition(0, "Product1")
        checkNameOnPosition(1, "Product2")

        activityScenario.close()
    }

    @Test
    fun testOpenProductDetailOnClick() {
        TestInjector(FakeRepositoryModule()).inject()
        val activityScenario: ActivityScenario<MainActivity> =
            ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.product_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )

        onView(withId(R.id.product_detail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.product_detail_name)).check(matches(withText("Product1")))

        activityScenario.close()
    }

    @Test
    fun testFetchProductError() {
        every { productUserRepository.getProducts() } returns Observable.error(IOException())

        TestInjector(FakeRepositoryModule(productUserRepository)).inject()
        val activityScenario: ActivityScenario<MainActivity> =
            ActivityScenario.launch(MainActivity::class.java)

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.error)))

        activityScenario.close()
    }

    @Test
    fun testProgressBarWhileLoading() {
        every { productUserRepository.getProducts() } returns Observable.create {}

        TestInjector(FakeRepositoryModule(productUserRepository)).inject()
        val activityScenario: ActivityScenario<MainActivity> =
            ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.progress_loading)).check(matches(isDisplayed()))
        activityScenario.close()
    }

    @Test
    fun testChangeTabLayout() {
        TestInjector(FakeRepositoryModule()).inject()
        val activityScenario: ActivityScenario<MainActivity> =
            ActivityScenario.launch(MainActivity::class.java)

        checkNameOnPosition(0, "Product1")
        onView(withId(R.id.categories_tab)).perform(selectTabAtPosition(1))
        checkNameOnPosition(0, "Product3")

        activityScenario.close()
    }

    @Test
    fun testEmptyProducts() {

        every { productUserRepository.getProducts() } returns Observable.just(listOf())

        TestInjector(FakeRepositoryModule(productUserRepository)).inject()
        val activityScenario: ActivityScenario<MainActivity> =
            ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.no_products_img)).check(matches(isDisplayed()))
        onView(withId(R.id.no_products_txt)).check(matches(isDisplayed()))

        activityScenario.close()
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
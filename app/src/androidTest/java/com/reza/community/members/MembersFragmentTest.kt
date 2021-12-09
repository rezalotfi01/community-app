package com.reza.community.members

import android.view.View
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.reza.community.BuildConfig
import com.reza.community.R
import com.reza.community.MainActivity
import com.reza.community.utils.TestConfigurationRule
import com.reza.community.utils.ViewVisibilityIdlingResource
import com.reza.community.webmock.ErrorDispatcher
import com.reza.community.webmock.SuccessDispatcher


@RunWith(AndroidJUnit4::class)
class MembersFragmentTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    @get:Rule
    val espressoRule = TestConfigurationRule()

    private val mockWebServer = MockWebServer()

    private var membersAnimationGoneIdlingResource: ViewVisibilityIdlingResource? = null

    @Before
    fun setup() {
        mockWebServer.start(BuildConfig.PORT)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(membersAnimationGoneIdlingResource)
    }

    @Test
    fun elementsVisibilityAfterOpeningTheScreen() {
        mockWebServer.dispatcher = SuccessDispatcher()

        membersAnimationGoneIdlingResource =
            ViewVisibilityIdlingResource(
                activityTestRule.activity.findViewById(R.id.lottieViewLoading),
                View.GONE
            )

        membersFragmentRobot {
            waitForCondition(membersAnimationGoneIdlingResource)
            assertRecyclerViewIsDisplayed()
        }
    }

    @Test
    fun itemsListed() {
        mockWebServer.dispatcher = SuccessDispatcher()

        membersAnimationGoneIdlingResource =
            ViewVisibilityIdlingResource(
                activityTestRule.activity.findViewById(R.id.lottieViewLoading),
                View.GONE
            )

        membersFragmentRobot {
            waitForCondition(membersAnimationGoneIdlingResource)
            assertRecyclerViewIsDisplayed()
            assertItemsSize()
        }
    }

    @Test
    fun clickItemShowBottomSheet() {
        mockWebServer.dispatcher = SuccessDispatcher()

        membersAnimationGoneIdlingResource =
            ViewVisibilityIdlingResource(
                activityTestRule.activity.findViewById(R.id.lottieViewLoading),
                View.GONE
            )

        membersFragmentRobot {
            waitForCondition(membersAnimationGoneIdlingResource)
            assertRecyclerViewIsDisplayed()
            clickItem(3)
        }
    }

    @Test
    fun clickItemAndShowDialog() {
        mockWebServer.dispatcher = SuccessDispatcher()

        membersAnimationGoneIdlingResource =
            ViewVisibilityIdlingResource(
                activityTestRule.activity.findViewById(R.id.lottieViewLoading),
                View.GONE
            )

        membersFragmentRobot {
            waitForCondition(membersAnimationGoneIdlingResource)
            assertRecyclerViewIsDisplayed()
        }
    }

    @Test
    fun displayBodyError() {
        mockWebServer.dispatcher = ErrorDispatcher()

        membersAnimationGoneIdlingResource =
            ViewVisibilityIdlingResource(
                activityTestRule.activity.findViewById(R.id.lottieViewLoading),
                View.GONE
            )

        membersFragmentRobot {
            waitForCondition(membersAnimationGoneIdlingResource)
            assertBodyErrorDisplayed()
        }
    }
}
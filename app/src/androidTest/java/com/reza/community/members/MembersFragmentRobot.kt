package com.reza.community.members

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.CoreMatchers.not
import com.reza.community.R
import com.reza.community.utils.RecyclerViewItemCountAssertion
import com.reza.community.utils.RecyclerViewMatcher

fun membersFragmentRobot(func: MembersFragmentRobot.() -> Unit) =
    MembersFragmentRobot().apply { func() }

class MembersFragmentRobot {

    fun waitForCondition(idlingResource: IdlingResource?) = apply {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    fun assertMembersTitleIsDisplayed() = apply {
        onView(membersTitleViewMatcher).check(
            matches(
                isDisplayed()
            )
        )
    }

    fun assertMembersAnimationIsDisplayed() = apply {
        onView(membersAnimationViewMatcher).check(
            matches(
                isDisplayed()
            )
        )
    }

    fun assertRecyclerViewIsNotDisplayed() = apply {
        onView(recyclerViewMatcher).check(matches(not(isDisplayed())))
    }

    fun assertRecyclerViewIsDisplayed() = apply {
        onView(recyclerViewMatcher).check(matches(isDisplayed()))
    }

    fun assertItemsSize() = apply {
        onView(recyclerViewMatcher).check(
            RecyclerViewItemCountAssertion(104)
        )
    }

    fun assertBodyErrorDisplayed() = apply {
        onView(membersErrorViewMatcher).check(matches(isDisplayed()))
    }

    fun clickItem(position: Int) = apply {
        val itemMatcher = RecyclerViewMatcher(recyclerViewId).atPosition(position)
        onView(itemMatcher).perform(ViewActions.click())
    }


    companion object {

        private const val recyclerViewId = R.id.recyclerMembers

        private val recyclerViewMatcher = withId(R.id.recyclerMembers)

        private val membersTitleViewMatcher = withId(R.id.txtTitle)

        private val membersAnimationViewMatcher = withId(R.id.lottieViewLoading)

        private val membersErrorViewMatcher = withId(R.id.txtErrorDescription)

        private val membersViewMatcher = withId(R.id.membersScreen)
    }
}
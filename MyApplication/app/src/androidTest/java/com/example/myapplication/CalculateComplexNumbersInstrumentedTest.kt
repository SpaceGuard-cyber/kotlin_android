package com.example.myapplication

import android.app.Activity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import org.hamcrest.Matchers.not

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CalculateComplexNumbersInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.myapplication", appContext.packageName)
    }

    @get:Rule // создаем правило какое активити запустить
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun divForIm(){
        //launchActivity<MainActivity>()

        onView(withId(R.id.editTextTexta)).perform(typeText("10"))
        onView(withId(R.id.editTextTextb)).perform(typeText("10"))
        onView(withId(R.id.editTextTextc)).perform(typeText("10"))
        onView(withId(R.id.editTextTextd)).perform(typeText("10"))
        onView(withId(R.id.buttondiv)).perform(click())
        onView(withId(R.id.textViewim)).check(matches(withText("0.0")))
    }
    @Test
    fun failedInputDivForIm(){
        Espresso.onView(withId(R.id.buttondiv)).perform(click())
        var activity:Activity? = null
        activityScenarioRule.scenario.onActivity{ activity = it}
            Espresso.onView(withText(R.string.first_number_error_a)).inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.`is`(activity!!.window.decorView)))).
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun divForRe(){
        //launchActivity<MainActivity>()
        onView(withId(R.id.editTextTexta)).perform(typeText("10"))
        onView(withId(R.id.editTextTextb)).perform(typeText("10"))
        onView(withId(R.id.editTextTextc)).perform(typeText("10"))
        onView(withId(R.id.editTextTextd)).perform(typeText("10"))
        onView(withId(R.id.buttondiv)).perform(click())
        onView(withId(R.id.textViewim)).check(matches(withText("1.0")))
    }
    @Test
    fun failedInputDivForRe(){
        Espresso.onView(withId(R.id.buttondiv)).perform(click())
        var activity:Activity? = null
        activityScenarioRule.scenario.onActivity{ activity = it}
        Espresso.onView(withText(R.string.first_number_error_a)).inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.`is`(activity!!.window.decorView)))).
        check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun mulForRe(){
        //launchActivity<MainActivity>()

        onView(withId(R.id.editTextTexta)).perform(typeText("10"))
        onView(withId(R.id.editTextTextb)).perform(typeText("10"))
        onView(withId(R.id.editTextTextc)).perform(typeText("10"))
        onView(withId(R.id.editTextTextd)).perform(typeText("10"))
        onView(withId(R.id.buttonmul)).perform(click())
        onView(withId(R.id.textViewim)).check(matches(withText("0.0")))
    }
    @Test
    fun failedInputMulForRe(){
        Espresso.onView(withId(R.id.buttonmul)).perform(click())
        var activity:Activity? = null
        activityScenarioRule.scenario.onActivity{ activity = it}
        Espresso.onView(withText(R.string.first_number_error_a)).inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.`is`(activity!!.window.decorView)))).
        check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun mulForIm(){
        //launchActivity<MainActivity>()

        onView(withId(R.id.editTextTexta)).perform(typeText("10"))
        onView(withId(R.id.editTextTextb)).perform(typeText("10"))
        onView(withId(R.id.editTextTextc)).perform(typeText("10"))
        onView(withId(R.id.editTextTextd)).perform(typeText("10"))
        onView(withId(R.id.buttonmul)).perform(click())
        onView(withId(R.id.textViewim)).check(matches(withText("200.0")))
    }
    @Test
    fun failedInputMulForIm(){
        Espresso.onView(withId(R.id.buttonmul)).perform(click())
        var activity:Activity? = null
        activityScenarioRule.scenario.onActivity{ activity = it}
        Espresso.onView(withText(R.string.first_number_error_a)).inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.`is`(activity!!.window.decorView)))).
        check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
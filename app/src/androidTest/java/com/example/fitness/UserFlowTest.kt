package com.example.fitness

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test

import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
class UserFlowTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testAddCategory() {
        composeTestRule.onNodeWithText("Settings")
            .performClick()

        composeTestRule.onNodeWithText("Exercises categories")
            .performClick()

        composeTestRule.onNodeWithText("Add category")
            .performClick()

        composeTestRule.onNodeWithText("Name")
            .performTextInput("Arms")

        composeTestRule.onNodeWithText("Description")
            .performTextInput("Exercises for improve your arm strength")

        composeTestRule.onNodeWithText("Create")
            .performClick()

        composeTestRule.onNodeWithText("Activity")
            .performClick()

        composeTestRule.onNodeWithText("Add activity")
            .performClick()

        composeTestRule.onNodeWithText("Not selected")
            .performClick()

        composeTestRule.onNodeWithText("Arms")
            .performClick()

        composeTestRule.onNodeWithText("Name")
            .performTextInput("Pushups")

        composeTestRule.onNodeWithText("Save")
            .performClick()
    }
}
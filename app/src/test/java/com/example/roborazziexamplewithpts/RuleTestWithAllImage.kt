package com.example.roborazziexamplewithpts

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.RoborazziRule.CaptureType
import com.github.takahirom.roborazzi.RoborazziRule.Options
import com.github.takahirom.roborazzi.captureRoboImage
import java.io.File
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class RuleTestWithAllImage {
  private var number = 0

  @get:Rule
  val roborazziRule = RoborazziRule(
    captureRoot = onView(isRoot()),
    options = Options(
      captureType = CaptureType.AllImage(),
      outputFileProvider = { description, directory, fileExtension ->
        File(
          directory,
          "custom-${description.testClass.name}.${description.methodName}.${number++}.$fileExtension"
        )
      }
    ),
  )

  @Test
  fun captureRoboGifSample() {
    // launch
    launch(MainActivity::class.java)
    // move to next page
    onView(withId(R.id.button_first))
      .perform(click())
    // back
    pressBack()
    // move to next page
    onView(withId(R.id.button_first))
      .perform(click())

    onView(isRoot())
      .captureRoboImage()
  }
}

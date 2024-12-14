package com.greenrobotdev.favily.screen.home

sealed class StoryHomeScreen {
  data object Home: StoryHomeScreen()
  class PlanDetails(val planId: String) : StoryHomeScreen()
  data object Create: StoryHomeScreen()
}

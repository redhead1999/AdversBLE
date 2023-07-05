package com.aold.advers.ble.domain.models

import com.aold.advers.ble.domain.models.AppRouteArgs.ADDRESS
import com.aold.advers.ble.domain.models.AppRoutes.CONTROL_SCREEN
import com.aold.advers.ble.domain.models.AppRoutes.HOME_SCREEN

object AppRoutes {
    const val HOME_SCREEN = "home"
    const val CONTROL_SCREEN = "control"
    const val HELP_ABOUT = "help_about"
    const val SETTINGS = "settings"
    const val TEST = "test"
}

object AppRouteArgs {
    const val ADDRESS = "address"
}

object AppDestinations {
    const val HOME = HOME_SCREEN
    const val CONTROL = "$CONTROL_SCREEN/{$ADDRESS}"
    const val HELP_ABOUT = AppRoutes.HELP_ABOUT
    const val SETTINGS = AppRoutes.SETTINGS
    const val TEST = AppRoutes.TEST
}
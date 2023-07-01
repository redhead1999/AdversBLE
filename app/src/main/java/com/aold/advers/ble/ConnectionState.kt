package com.aold.advers.ble

/**
 * @author Kirilin Yury on 02.07.2023.
 */
sealed interface ConnectionState{
    object Connected: ConnectionState
    object Disconnected: ConnectionState
    object Uninitialized: ConnectionState
    object CurrentlyInitializing: ConnectionState
}
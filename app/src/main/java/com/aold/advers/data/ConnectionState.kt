package com.aold.advers.data

/**
 * @author Kirilin Yury on 09.06.2023.
 */
sealed interface ConnectionState {
    object Connecting: ConnectionState
    object Connected: ConnectionState
    object Disconnected: ConnectionState
    object Uninitialized: ConnectionState
}
package com.aold.advers.presentation.components.device

import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.aold.advers.R
import com.aold.advers.ble.ConnectionEventListener
import com.aold.advers.ble.toHexString

/**
 * @author Kirilin Yury on 01.07.2023.
 */

//@Composable
//fun DeviceCharacterItem(){
//    AndroidView(
//        factory = { context ->
//            val view = LayoutInflater.from(context).inflate(R.layout.activity_ble_operations, null, false)
//            private val connectionEventListener by lazy {
//                ConnectionEventListener().apply {
//                    onDisconnect = {
//                        runOnUiThread {
//                            //alertdialog
//                        }
//                    }
//
//                    onCharacteristicRead = { _, characteristic ->
//                        log("Read from ${characteristic.uuid}: ${characteristic.value.toHexString()}")
//                    }
//
//                    onCharacteristicWrite = { _, characteristic ->
//                        log("Wrote to ${characteristic.uuid}")
//                    }
//
//                    onMtuChanged = { _, mtu ->
//                        log("MTU updated to $mtu")
//                    }
//
//                    onCharacteristicChanged = { _, characteristic ->
//                        log("Value changed on ${characteristic.uuid}: ${characteristic.value.toHexString()}")
//                    }
//
//                    onNotificationsEnabled = { _, characteristic ->
//                        log("Enabled notifications on ${characteristic.uuid}")
//                        notifyingCharacteristics.add(characteristic.uuid)
//                    }
//
//                    onNotificationsDisabled = { _, characteristic ->
//                        log("Disabled notifications on ${characteristic.uuid}")
//                        notifyingCharacteristics.remove(characteristic.uuid)
//                    }
//                }
//            }
//            view // return the view
//        },
//        update = { view ->
//            // Update the view
//        }
//    )
//
//    }
//}
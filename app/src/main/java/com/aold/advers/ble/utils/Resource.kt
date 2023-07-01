package com.aold.advers.ble.utils

/**
 * @author Kirilin Yury on 09.06.2023.
 */
sealed class Resource<out T:Any>{
    data class Success<out T:Any> (val data:T):Resource<T>()
    data class Error(val errorMessage:String):Resource<Nothing>()
    data class Loading<out T:Any>(val data:T? = null, val message:String? = null):Resource<T>()
}
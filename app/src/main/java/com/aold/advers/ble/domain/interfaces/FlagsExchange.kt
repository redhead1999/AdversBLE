package com.aold.advers.ble.domain.interfaces

data class FlagsExchange(val param: LongArray) {
    var isSetter = param
    var isEmpty = param
    var isWait = param

    override
    fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FlagsExchange

        if (!param.contentEquals(other.param)) return false
        if (isSetter != other.isSetter) return false
        if (isEmpty != other.isEmpty) return false
        if (isWait != other.isWait) return false

        return true
    }

    override fun hashCode(): Int {
        var result = param.contentHashCode()
        result = 31 * result + isSetter.hashCode()
        result = 31 * result + isEmpty.hashCode()
        result = 31 * result + isWait.hashCode()
        return result
    }
}
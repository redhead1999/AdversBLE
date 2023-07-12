package com.aold.advers.ble.domain.models

import com.aold.advers.R

enum class ScanFilterOption{
    RSSI,
    NAME,
    FAVORITES,
    FORGET
}

data class ScanFilter(
    val filterOption: ScanFilterOption,
    val icon: Int,
    val text: String
)

val SCAN_FILTERS = listOf(
    ScanFilter(ScanFilterOption.RSSI, R.drawable.signal, "Поиск"),
    ScanFilter(ScanFilterOption.NAME, R.drawable.az_sort, "Мои"),
    ScanFilter(ScanFilterOption.FAVORITES, R.drawable.favorite_selected, "Избранное"),
    ScanFilter(ScanFilterOption.FORGET, R.drawable.delete_forever, "Удалены"),
)

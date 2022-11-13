package pl.bachorski.domain.adapters

import pl.bachorski.domain.DeviceBinarySwitch

interface HomeApi {
    suspend fun getDevices(): List<DeviceBinarySwitch>
    suspend fun getDevicesSortedByRooms(): List<DeviceBinarySwitch>
}
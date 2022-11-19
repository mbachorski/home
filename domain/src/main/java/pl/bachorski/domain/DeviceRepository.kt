package pl.bachorski.domain

import pl.bachorski.domain.adapters.HomeApi

class DeviceRepository(
    private val homeApi: HomeApi,
    private val devicesCache: DevicesCache
) {

    suspend fun getDevices(): List<DeviceBinarySwitch> {
        return if (devicesCache.hasDevices()) {
            devicesCache.devices
        } else {
            val devices = homeApi.getDevicesSortedByRooms()
            devicesCache.setDevices(devices)
            devices
        }
    }
}
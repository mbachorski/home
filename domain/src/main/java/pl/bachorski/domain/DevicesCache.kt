package pl.bachorski.domain

class DevicesCache {
    private val _devices = mutableListOf<DeviceBinarySwitch>()

    val devices: List<DeviceBinarySwitch>
        get() = _devices

    fun hasDevices() = devices.isNotEmpty()

    fun setDevices(devices: List<DeviceBinarySwitch>) {
        _devices.clear()
        _devices.addAll(devices)
    }
}
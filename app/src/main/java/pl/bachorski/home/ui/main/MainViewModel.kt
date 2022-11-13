package pl.bachorski.home.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.bachorski.domain.DeviceBinarySwitch
import pl.bachorski.domain.DeviceRepository
import pl.bachorski.domain.DevicesCache
import pl.bachorski.home.api.ApiAdapter
import pl.bachorski.home.api.DevicesApiRetrofitService
import pl.bachorski.home.api.HomeApiRetrofitService

class MainViewModel : ViewModel() {

    private val _devices = MutableLiveData<List<DeviceBinarySwitch>>()

    val devices: LiveData<List<DeviceBinarySwitch>> = _devices

    init {
//        getDevicesFromRepository()
    }

    // TODO DI
    private val deviceRepository by lazy {
        DeviceRepository(
            ApiAdapter(HomeApiRetrofitService.homeApiService),
            DevicesCache()
        )
    }

    // TODO DI
    private val devicesApiService by lazy {
        DevicesApiRetrofitService.devicesApiService
    }

    fun getDevicesFromRepository() {
        viewModelScope.launch {
            _devices.value = deviceRepository.getDevices() ?: listOf() // build error without ?:
            Log.v("HOME", devices.value.toString())
        }
    }

    fun turnOn(deviceId: Int) {
        viewModelScope.launch {
            devicesApiService.turnOn(deviceId)
            Log.v("HOME", "turning ON")
        }
    }

    fun turnOff(deviceId: Int) {
        viewModelScope.launch {
            devicesApiService.turnOff(deviceId)
            Log.v("HOME", "turning OFF")
        }
    }
}
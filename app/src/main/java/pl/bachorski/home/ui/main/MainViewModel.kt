package pl.bachorski.home.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.bachorski.domain.DeviceRepository
import pl.bachorski.domain.DevicesCache
import pl.bachorski.home.api.ApiAdapter
import pl.bachorski.home.api.HomeApiRetrofitService

class MainViewModel : ViewModel() {
    // TODO DI
    private val deviceRepository = DeviceRepository(
        ApiAdapter(HomeApiRetrofitService.retrofitService),
        DevicesCache()
    )

    fun getDevices() {
        viewModelScope.launch {
            val devices = deviceRepository.getDevices()
            Log.v("HOME", devices.toString())
        }
    }
}
package pl.bachorski.home.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.bachorski.home.api.HomeApi

class MainViewModel : ViewModel() {
    fun getDevices() {
        viewModelScope.launch {
            val devices = HomeApi.retrofitService.getDevices()
            Log.v("HOME", devices.toString())
        }
    }
}
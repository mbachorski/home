package pl.bachorski.home.api

import pl.bachorski.domain.DeviceBinarySwitch
import pl.bachorski.domain.adapters.HomeApi

class ApiAdapter(private val apiService: HomeApiService) : HomeApi {
    override suspend fun getDevices(): List<DeviceBinarySwitch> {
        return apiService.getDevices()
            .filter {
                val type = it.get("type").asString
                type == "com.fibaro.binarySwitch"
            }
            .map {
                DeviceBinarySwitch(
                    it.get("id").asInt,
                    it.get("name").asString,
                    it.get("properties").asJsonObject.get("value").asBoolean
                )
            }
    }
}
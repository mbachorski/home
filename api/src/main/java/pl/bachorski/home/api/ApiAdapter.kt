package pl.bachorski.home.api

import pl.bachorski.domain.DeviceBinarySwitch
import pl.bachorski.domain.Room
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
                    it.get("roomID").asInt,
                    it.get("properties").asJsonObject.get("value").asBoolean,
                    it.get("sortOrder").asInt
                )
            }
    }

    override suspend fun getDevicesSortedByRooms(): List<DeviceBinarySwitch> {
        val rooms = apiService.getRooms()
        val devices = getDevices()
        return devices.sortedWith(
            compareBy(
                { getRoomSortOrder(rooms, it.roomId) },
                { it.sortOrder }
            )
        )
    }

    private fun getRoomSortOrder(rooms: List<Room>, roomId: Int): Int {
        for (room in rooms) {
            if (room.id == roomId) return room.sortOrder
        }
        return 0
    }
}
package pl.bachorski.home.api.fakes

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import pl.bachorski.domain.Room
import pl.bachorski.home.api.HomeApiService
import java.lang.reflect.Type

inline fun <reified T> String.convertToListObject(): List<T>? {
    val listType: Type = object : TypeToken<List<T?>?>() {}.type
    return Gson().fromJson<List<T>>(
        this,
        listType
    )
}

class FakeApiService : HomeApiService {
    override suspend fun getDevices(): List<JsonObject> {
        val jsonObject = Gson().fromJson<JsonObject>(testData, JsonObject::class.java)
        return listOf(jsonObject)
    }

    override suspend fun getRooms(): List<Room> {
        return roomsTestData.convertToListObject<Room>()!!
    }
}

private val roomsTestData = """
[    
    {
    "id": 21,
    "name": "Wardrobe",
    "sectionID": 6,
    "icon": "room_guest",
    "defaultSensors": {
    "temperature": 0,
    "humidity": 0,
    "light": 0
    },
    "defaultThermostat": 0,
    "sortOrder": 13,
    "category": "other"
    },
    {
    "id": 22,
    "name": "My room",
    "sectionID": 6,
    "icon": "User1001",
    "defaultSensors": {
    "temperature": 0,
    "humidity": 0,
    "light": 0
    },
    "defaultThermostat": 0,
    "sortOrder": 11,
    "category": "other"
    }
]
"""

private val testData = """
{
   "id":20,
   "name":"19.0",
   "roomID":0,
   "type":"com.fibaro.binarySwitch",
   "baseType":"com.fibaro.actor",
   "enabled":true,
   "visible":false,
   "isPlugin":false,
   "parentId":19,
   "remoteGatewayId":0,
   "viewXml":false,
   "configXml":false,
   "interfaces":[
      "deviceGrouping",
      "energy",
      "fibaroFirmwareUpdate",
      "light",
      "power",
      "zwave",
      "zwaveAlarm",
      "zwaveMultiChannelAssociation",
      "zwaveProtection"
   ],
   "properties":{
      "pollingTimeSec":0,
      "zwaveCompany":"Fibargroup",
      "zwaveInfo":"3,4,5",
      "zwaveVersion":"3.3",
      "RFProtectionState":"0",
      "RFProtectionSupport":"3",
      "alarmLevel":"0",
      "alarmType":"0",
      "categories":"[\"lights\"]",
      "configured":true,
      "dead":"true",
      "deadReason":"power",
      "deviceControlType":"2",
      "deviceGroup":"[]",
      "deviceGroupMaster":"0",
      "deviceIcon":"2",
      "emailNotificationID":"0",
      "emailNotificationType":"0",
      "endPointId":"0",
      "energy":"0.27",
      "firmwareUpdate":"{\"info\":\"\",\"progress\":0,\"status\":\"Available\",\"updateVersion\":\"3.4\"}",
      "isLight":"true",
      "localProtectionState":"0",
      "localProtectionSupport":"5",
      "log":"",
      "logTemp":"",
      "manufacturer":"",
      "markAsDead":"true",
      "model":"",
      "nodeId":"5",
      "parametersTemplate":"781",
      "power":"0.00",
      "productInfo":"1,15,2,3,16,0,3,3",
      "protectionExclusiveControl":"0",
      "protectionExclusiveControlSupport":"false",
      "protectionState":"0",
      "protectionTimeout":"0",
      "protectionTimeoutSupport":"false",
      "pushNotificationID":"0",
      "pushNotificationType":"0",
      "remoteGatewayId":"0",
      "saveLogs":"true",
      "serialNumber":"h'000000000000e6ba",
      "showEnergy":"true",
      "smsNotificationID":"0",
      "smsNotificationType":"0",
      "updateVersion":"",
      "useTemplate":"true",
      "userDescription":"",
      "value":"false"
   },
   "actions":{
      "abortUpdate":1,
      "reconfigure":0,
      "reset":0,
      "retryUpdate":1,
      "startUpdate":1,
      "turnOff":0,
      "turnOn":0,
      "updateFirmware":1
   },
   "created":1659171224,
   "modified":1659171224,
   "sortOrder":14
}
"""
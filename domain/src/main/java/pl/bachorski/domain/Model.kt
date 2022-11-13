package pl.bachorski.domain

data class DeviceBinarySwitch(
    val id: Int,
    val name: String,
    val roomId: Int,
    val value: Boolean,
    val sortOrder: Int
)

data class Room(
    val id: Int,
    val name: String,
    val sortOrder: Int
){

}
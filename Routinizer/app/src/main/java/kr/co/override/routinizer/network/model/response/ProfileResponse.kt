package kr.co.override.routinizer.network.model.response

data class ProfileResponse(
    val name: String,
    val avatar: String,
    val school: String,
    val point: String,
    val grade: Int,
    val count: Int,
    val continuous: Int
)
package kr.co.override.routinizer.network.model.request

data class LoginRequest(
    val id : String,
    val password : String
)

data class RegisterRequest(
    val name : String,
    val school : String,
    val id : String,
    val password : String
)

package kr.co.override.routinizer.network.api

import kr.co.override.routinizer.network.model.request.LoginRequest
import kr.co.override.routinizer.network.model.request.RegisterRequest
import kr.co.override.routinizer.network.model.response.LoginResponse
import kr.co.override.routinizer.network.model.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Sign {
    @POST("auth/login")
    fun login(@Body loginRequest: LoginRequest) : retrofit2.Call<LoginResponse>

    @POST("auth/join")
    fun register(@Body registerRequest: RegisterRequest) : retrofit2.Call<RegisterResponse>
}
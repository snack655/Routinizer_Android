package kr.co.override.routinizer.network.api

import kr.co.override.routinizer.network.model.request.LoginRequest
import kr.co.override.routinizer.network.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Sign {
    @POST("auth/login")
    fun login(@Body loginRequest: LoginRequest) : retrofit2.Call<LoginResponse>
}
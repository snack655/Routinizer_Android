package kr.co.override.routinizer.network.api

import kr.co.override.routinizer.network.model.response.PostedResponse
import kr.co.override.routinizer.network.model.response.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET

interface Profile {
    @GET("auth/profile")
    fun getProfiles() : Call<ProfileResponse>
}
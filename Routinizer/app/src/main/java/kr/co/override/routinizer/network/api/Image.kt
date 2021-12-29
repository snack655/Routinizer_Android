package kr.co.override.routinizer.network.api

import kr.co.override.routinizer.network.model.response.ImageResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Image {
    @Multipart
    @POST("upload")
    fun imageUpload(@Part img: List<MultipartBody.Part>?) : retrofit2.Call<ImageResponse>
}
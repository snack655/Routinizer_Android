package kr.co.override.routinizer.network

import com.google.gson.GsonBuilder
import kr.co.override.routinizer.network.api.Image
import kr.co.override.routinizer.network.api.Posted
import kr.co.override.routinizer.network.api.Profile
import kr.co.override.routinizer.network.api.Sign
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient{

    private const val BASE_URL = "http://192.168.177.67:4000/api/v1/"

    //val loginInterface: Login
    val signInterface : Sign
    val imageInterface : Image
    val postedInterface : Posted
    val getProfileInterface : Profile

    private val gson = GsonBuilder().setLenient().create()
    private val intercepter = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val logger = OkHttpClient.Builder().addInterceptor(intercepter).addInterceptor(TokenInterceptor())
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100, TimeUnit.SECONDS)
        .build()

    val instance: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(logger)
        .build()

    init {
        signInterface = instance.create(Sign::class.java)
        imageInterface = instance.create(Image::class.java)
        postedInterface = instance.create(Posted::class.java)
        getProfileInterface = instance.create(Profile::class.java)
    }
}
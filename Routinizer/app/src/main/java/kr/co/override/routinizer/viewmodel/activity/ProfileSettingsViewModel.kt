package kr.co.override.routinizer.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent
import kr.co.override.routinizer.network.RetrofitClient
import kr.co.override.routinizer.network.model.request.RegisterRequest
import kr.co.override.routinizer.network.model.response.ErrorResponse
import kr.co.override.routinizer.network.model.response.ImageResponse
import kr.co.override.routinizer.network.model.response.RegisterResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File

class ProfileSettingsViewModel: ViewModel() {
    val onProfileEvent = SingleLiveEvent<Unit>()
    val onBackEvent = SingleLiveEvent<Unit>()
    val onImageEvent = SingleLiveEvent<Unit>()
    val onConfirmEvent = SingleLiveEvent<Unit>()

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val school = MutableLiveData<String>()
    var img = MutableLiveData<ArrayList<File>>(arrayListOf())
    val avatar = MutableLiveData<String>()
    val message = MutableLiveData<String>()



    fun onClickProfile() {
        val registerRequest = RegisterRequest(
            name.value ?: "",
            school.value ?: "",
            id.value ?: "",
            pw.value ?: "",
            avatar.value ?: ""
        )
        RetrofitClient.signInterface.register(registerRequest)
            .enqueue(object : retrofit2.Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        onProfileEvent.call()
                    } else {
                        val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                            ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                        message.value = errorBody?.message
                        Log.d("Retrofit2", "onResponse: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.d("Retrofit2", "onFailure: $t")
                }

        })

    }

    fun onClickConfirm() {
        val imageCall = RetrofitClient.imageInterface.imageUpload(
            img.value?.map { MultipartBody.Part.createFormData(
                "image",
                it.name,
                RequestBody.create("image/${it.name.split(".")[1]}".toMediaTypeOrNull(), it)
            )}
        )

        imageCall.enqueue(object : retrofit2.Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                if (response.isSuccessful) {
                    avatar.value = response.body()?.image.toString()
                    onConfirmEvent.call()
                } else {
                    val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                        ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                    message.value = errorBody?.message
                    Log.d("Retrofit2", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })


    }

    fun onClickImage() {
        onImageEvent.call()
    }

    fun onClickBack(){
        onBackEvent.call()
    }
}
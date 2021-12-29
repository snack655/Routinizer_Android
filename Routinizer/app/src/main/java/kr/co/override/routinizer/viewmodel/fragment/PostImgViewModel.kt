package kr.co.override.routinizer.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent
import kr.co.override.routinizer.network.RetrofitClient
import kr.co.override.routinizer.network.model.response.ErrorResponse
import kr.co.override.routinizer.network.model.response.ImageResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File

class PostImgViewModel: ViewModel() {
    val onNextEvent = SingleLiveEvent<Unit>()
    val onBackEvent = SingleLiveEvent<Unit>()

    val onSelectEvent = SingleLiveEvent<Unit>()

    var img = MutableLiveData<ArrayList<File>>(arrayListOf())

    val reImg = MutableLiveData<String>()
    val message = MutableLiveData<String>()


    fun onClickNext() {
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
                    reImg.value = response.body()?.image.toString()
                    onNextEvent.call()
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

    fun onClickSelect() {
        onSelectEvent.call()
    }

    fun onClickBack() {
        onBackEvent.call()
    }

}
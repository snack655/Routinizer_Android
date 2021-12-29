package kr.co.override.routinizer.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent
import kr.co.override.routinizer.network.RetrofitClient
import kr.co.override.routinizer.network.model.request.PostingRequest
import kr.co.override.routinizer.network.model.response.ErrorResponse
import kr.co.override.routinizer.network.model.response.PostingResponse
import retrofit2.Call
import retrofit2.Response

class PostGoodViewModel: ViewModel() {
    val title = MutableLiveData<String>()
    val category = MutableLiveData<String>()
    val benefit = MutableLiveData<String>()
    val image = MutableLiveData<String>()

    val message = MutableLiveData<String>()

    val onNextEvent = SingleLiveEvent<Unit>()
    val onBackEvent = SingleLiveEvent<Unit>()
    val onInstanceEvent = SingleLiveEvent<Unit>()

    fun onClickNext() {
        onInstanceEvent.call()
        val postingRequest = PostingRequest(
            title.value ?: "",
            category.value ?: "",
            benefit.value ?: "",
            image.value ?: ""
        )

        RetrofitClient.postedInterface.posting(postingRequest)
            .enqueue(object : retrofit2.Callback<PostingResponse> {
                override fun onResponse(
                    call: Call<PostingResponse>,
                    response: Response<PostingResponse>
                ) {
                    if (response.isSuccessful) {
                        onNextEvent.call()
                    } else {
                        val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                            ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                        message.value = errorBody?.message
                    }
                }

                override fun onFailure(call: Call<PostingResponse>, t: Throwable) {
                    Log.d("Retrofit2", "onFailure: $t")
                }

            })

    }

    fun onClickBack() {
        onBackEvent.call()
    }

}
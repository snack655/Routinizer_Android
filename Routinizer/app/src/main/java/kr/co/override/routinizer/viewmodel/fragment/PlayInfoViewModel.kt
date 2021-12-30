package kr.co.override.routinizer.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent
import kr.co.override.routinizer.network.RetrofitClient
import kr.co.override.routinizer.network.model.response.DetailResponse
import kr.co.override.routinizer.network.model.response.ErrorResponse
import kr.co.override.routinizer.view.fragment.PlayInfoFragmentArgs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayInfoViewModel: ViewModel() {
    val onNextEvent = SingleLiveEvent<Unit>()
    val onBackEvent = SingleLiveEvent<Unit>()
    val onSuccessEvent = SingleLiveEvent<Unit>()

    val message = MutableLiveData<String>()

    val title = MutableLiveData<String>()
    val image = MutableLiveData<String>()
    val category = MutableLiveData<String>()
    val benefit = MutableLiveData<String>()
    val participationCount = MutableLiveData<Int>()
    val continuous = MutableLiveData<Int>()

    fun onClickNext() {
        onNextEvent.call()
    }

    fun onClickBack() {
        onBackEvent.call()
    }

    fun getDetailPost(id: String) {
        val getDetailCall = RetrofitClient.postedInterface.detailPost(id)

        getDetailCall.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful) {
                    onSuccessEvent.call()

                    val result = response.body()
                    title.value = result?.title
                    image.value = result?.image
                    category.value = result?.category
                    benefit.value = result?.benefit
                    participationCount.value = result?.participationCount
                    continuous.value = result?.continuous
                    Log.d("Retrofit2", "onResponse: 성공 Detail")
                } else {
                    val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                        ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                    message.value = errorBody?.message
                    Log.d("Retrofit2", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })
    }

}
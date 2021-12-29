package kr.co.override.routinizer.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent
import kr.co.override.routinizer.network.RetrofitClient
import kr.co.override.routinizer.network.model.response.ErrorResponse
import kr.co.override.routinizer.network.model.response.InvitedResponse
import kr.co.override.routinizer.network.model.response.participation
import retrofit2.Call
import retrofit2.Response

class PostViewModel: ViewModel() {
    val onNextEvent = SingleLiveEvent<Unit>()
    val message = MutableLiveData<String>()
    val myPostsList = MutableLiveData<List<participation>>()

    fun getMyPosts(){
        val myPostCall = RetrofitClient.postedInterface.getMyInvitedPost()

        myPostCall.enqueue(object : retrofit2.Callback<InvitedResponse> {
            override fun onResponse(
                call: Call<InvitedResponse>,
                response: Response<InvitedResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    myPostsList.value = result?.participations

                } else {
                    val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                        ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                    message.value = errorBody?.message
                    Log.d("Retrofit2", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<InvitedResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })
    }

    fun onClickNext() {
        onNextEvent.call()
    }

}
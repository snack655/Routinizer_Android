package kr.co.override.routinizer.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.network.RetrofitClient
import kr.co.override.routinizer.network.model.response.ErrorResponse
import kr.co.override.routinizer.network.model.response.InvitedResponse
import kr.co.override.routinizer.network.model.response.PostedResponse
import kr.co.override.routinizer.network.model.response.challenge
import okhttp3.Challenge
import retrofit2.Call
import retrofit2.Response

class PlayViewModel: ViewModel() {
    val message = MutableLiveData<String>()
    val myInvitedPostsList = MutableLiveData<List<challenge>>()

    fun getMyInvitedPosts() {
        val myInvitedCall = RetrofitClient.postedInterface.getMyInvitedPost()

        myInvitedCall.enqueue(object : retrofit2.Callback<PostedResponse> {
            override fun onResponse(
                call: Call<PostedResponse>,
                response: Response<PostedResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    myInvitedPostsList.value = result?.challenges

                } else {
                    val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                    ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                    message.value = errorBody?.message
                    Log.d("Retrofit2", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PostedResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })

    }
}
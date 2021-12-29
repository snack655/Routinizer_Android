package kr.co.override.routinizer.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.network.RetrofitClient
import kr.co.override.routinizer.network.model.response.ErrorResponse
import kr.co.override.routinizer.network.model.response.RankingResponse
import kr.co.override.routinizer.network.model.response.user
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankViewModel: ViewModel() {
    val message = MutableLiveData<String>()
    val allRankingList = MutableLiveData<List<user>>()

    fun getAllRanking() {
        val getAllRankingCall = RetrofitClient.postedInterface.getAllRanking()
        getAllRankingCall.enqueue(object : Callback<RankingResponse> {
            override fun onResponse(
                call: Call<RankingResponse>,
                response: Response<RankingResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    allRankingList.value = result?.users
                } else {
                    val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                        ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                    message.value = errorBody?.message
                    Log.d("Retrofit2", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RankingResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }
        })
    }
}
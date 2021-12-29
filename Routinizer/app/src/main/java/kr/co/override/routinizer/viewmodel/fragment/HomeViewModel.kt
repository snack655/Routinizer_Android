package kr.co.override.routinizer.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.network.RetrofitClient
import kr.co.override.routinizer.network.model.response.ErrorResponse
import kr.co.override.routinizer.network.model.response.PostedResponse
import kr.co.override.routinizer.network.model.response.challenge
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    val message = MutableLiveData<String>()
    val hotPostsList = MutableLiveData<List<challenge>>()
    val newPostsList = MutableLiveData<List<challenge>>()
    val exercisePostsList = MutableLiveData<List<challenge>>()

    fun getMainPosts() {

        val getHotPostsCall = RetrofitClient.postedInterface.getHotPosts()
        getHotPostsCall.enqueue(object : Callback<PostedResponse> {
            override fun onResponse(
                call: Call<PostedResponse>,
                response: Response<PostedResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    hotPostsList.value = result?.challenges
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

        val getNewPostsCall = RetrofitClient.postedInterface.getNewPosts()
        getNewPostsCall.enqueue(object : Callback<PostedResponse> {
            override fun onResponse(
                call: Call<PostedResponse>,
                response: Response<PostedResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    newPostsList.value = result?.challenges
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

        val getExercisePostsCall = RetrofitClient.postedInterface.getExercisePosts()
        getExercisePostsCall.enqueue(object : Callback<PostedResponse> {
            override fun onResponse(
                call: Call<PostedResponse>,
                response: Response<PostedResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    exercisePostsList.value = result?.challenges
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
package kr.co.override.routinizer.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent
import kr.co.override.routinizer.network.RetrofitClient
import kr.co.override.routinizer.network.model.response.ErrorResponse
import kr.co.override.routinizer.network.model.response.InvitedResponse
import kr.co.override.routinizer.network.model.response.ProfileResponse
import retrofit2.Call
import retrofit2.Response

class ProfileViewModel: ViewModel() {
    val onSuccessEvent = SingleLiveEvent<Unit>()
    val message = MutableLiveData<String>()

    val name = MutableLiveData<String>()
    val avatar = MutableLiveData<String>()
    val school = MutableLiveData<String>()
    val point = MutableLiveData<String>()
    val grade = MutableLiveData<Int>()
    val count = MutableLiveData<Int>()
    val continuous = MutableLiveData<Int>()

    fun getMyProfile() {
        val myProfileCall = RetrofitClient.getProfileInterface.getProfiles()

        myProfileCall.enqueue(object : retrofit2.Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    name.value = result?.name
                    avatar.value = result?.avatar
                    school.value = result?.school
                    point.value = result?.point
                    grade.value = result?.grade
                    count.value = result?.count
                    continuous.value = result?.continuous

                    onSuccessEvent.call()
                } else {
                    val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                        ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                    message.value = errorBody?.message
                    Log.d("Retrofit2", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })

    }


}
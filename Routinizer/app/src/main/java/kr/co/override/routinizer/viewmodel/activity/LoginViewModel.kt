package kr.co.override.routinizer.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent
import kr.co.override.routinizer.network.RetrofitClient
import kr.co.override.routinizer.network.model.request.LoginRequest
import kr.co.override.routinizer.network.model.response.ErrorResponse
import kr.co.override.routinizer.network.model.response.LoginResponse
import retrofit2.Call
import retrofit2.Response

class LoginViewModel: ViewModel() {
    val onLoginEvent = SingleLiveEvent<Unit>()
    val onBackEvent = SingleLiveEvent<Unit>()

    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val token = MutableLiveData<String>()
    val message = MutableLiveData<String>()

    fun onClickLogin() {
        val loginRequest = LoginRequest(
            id.value ?: "",
            password.value ?: ""
        )

        RetrofitClient.signInterface.login(loginRequest)
            .enqueue(object : retrofit2.Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        token.value = response.body()?.token
                        onLoginEvent.call()
                    } else {
                        val errorBody = RetrofitClient.instance.responseBodyConverter<ErrorResponse>(
                            ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(response.errorBody())
                        message.value = errorBody?.message
                        Log.d("Retrofit2", "onResponse: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("Retrofit2", "onFailure: $t")
                }
            })
    }

    fun onClickBack() {
        onBackEvent.call()
    }


}
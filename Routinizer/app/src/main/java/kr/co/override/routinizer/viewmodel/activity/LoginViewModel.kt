package kr.co.override.routinizer.viewmodel.activity

import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent

class LoginViewModel: ViewModel() {
    val onLoginEvent = SingleLiveEvent<Unit>()
    val onBackEvent = SingleLiveEvent<Unit>()

    fun onClickLogin() {
        onLoginEvent.call()
    }

    fun onClickBack() {
        onBackEvent.call()
    }


}
package kr.co.override.routinizer.viewmodel.activity

import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent

class RegisterViewModel: ViewModel() {
    val onRegisterEvent = SingleLiveEvent<Unit>()
    val toLoginEvent = SingleLiveEvent<Unit>()

    fun onClickRegister() {
        onRegisterEvent.call()
    }

    fun toClickLogin() {
        toLoginEvent.call()
    }

}
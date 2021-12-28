package kr.co.override.routinizer.viewmodel.activity

import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent

class ProfileSettingsViewModel: ViewModel() {
    val onProfileEvent = SingleLiveEvent<Unit>()
    val onBackEvent = SingleLiveEvent<Unit>()

    fun onClickProfile() {
        onProfileEvent.call()
    }

    fun onClickBack(){
        onBackEvent.call()
    }
}
package kr.co.override.routinizer.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent

class ProfileSettingsViewModel: ViewModel() {
    val onProfileEvent = SingleLiveEvent<Unit>()
    val onBackEvent = SingleLiveEvent<Unit>()

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val school = MutableLiveData<String>()



    fun onClickProfile() {
        onProfileEvent.call()
    }

    fun onClickBack(){
        onBackEvent.call()
    }
}
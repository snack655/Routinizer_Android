package kr.co.override.routinizer.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent

class PostTitleViewModel: ViewModel() {
    val onNextEvent = SingleLiveEvent<Unit>()
    val onBackEvent = SingleLiveEvent<Unit>()

    val titleV = MutableLiveData<String>()

    fun onClickNext() {
        onNextEvent.call()
    }

    fun onClickBack() {
        onBackEvent.call()
    }

}
package kr.co.override.routinizer.viewmodel.fragment

import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent

class PostGoodViewModel: ViewModel() {
    val onNextEvent = SingleLiveEvent<Unit>()
    val onBackEvent = SingleLiveEvent<Unit>()

    fun onClickNext() {
        onNextEvent.call()
    }

    fun onClickBack() {
        onBackEvent.call()
    }

}
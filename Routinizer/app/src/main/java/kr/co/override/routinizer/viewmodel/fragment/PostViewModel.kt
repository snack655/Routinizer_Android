package kr.co.override.routinizer.viewmodel.fragment

import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent

class PostViewModel: ViewModel() {
    val onNextEvent = SingleLiveEvent<Unit>()

    fun onClickNext() {
        onNextEvent.call()
    }

}
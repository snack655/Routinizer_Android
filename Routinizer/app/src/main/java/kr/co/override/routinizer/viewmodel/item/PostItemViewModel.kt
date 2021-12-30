package kr.co.override.routinizer.viewmodel.item

import androidx.lifecycle.ViewModel
import kr.co.override.routinizer.extension.SingleLiveEvent
import kr.co.override.routinizer.network.model.response.challenge

class PostItemViewModel(val challenge: challenge): ViewModel() {
    val onPostEvent = SingleLiveEvent<String>()

    fun onClickPost() {
        onPostEvent.value = challenge.id
    }
}
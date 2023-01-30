package tymofiy.dev.testsmatrapp.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import tymofiy.dev.testsmatrapp.data.Repository

class WebViewViewModel(private val repository: Repository) : ViewModel() {

    val baseUrlMutableLiveData = MutableLiveData<String?>()

    init {
        val baseUrl = "https://www.google.com/"
        baseUrlMutableLiveData.value = baseUrl
    }
}
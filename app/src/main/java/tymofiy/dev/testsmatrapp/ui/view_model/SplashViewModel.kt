package tymofiy.dev.testsmatrapp.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tymofiy.dev.testsmatrapp.data.Repository
import tymofiy.dev.testsmatrapp.data.local.mapper.toDomain
import tymofiy.dev.testsmatrapp.utils.SharedPreferencesUtils

class SplashViewModel(
    private val repository: Repository,
    private val shStorage: SharedPreferencesUtils
) : ViewModel() {

    var userStatus = MutableLiveData<Boolean>()

    fun getUserStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadGist()
            userStatus.postValue(shStorage.getUserState() ?: false)
        }
    }
}
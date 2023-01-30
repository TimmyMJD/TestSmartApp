package tymofiy.dev.testsmatrapp.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tymofiy.dev.testsmatrapp.data.Repository
import tymofiy.dev.testsmatrapp.utils.SharedPreferencesUtils

class ViewModelFactory(private val repository: Repository, private val shStorage: SharedPreferencesUtils) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SplashViewModel(repository, shStorage) as T
        }
        if (modelClass.isAssignableFrom(WebViewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WebViewViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
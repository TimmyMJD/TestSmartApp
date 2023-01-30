package tymofiy.dev.testsmatrapp.base

import android.app.Application
import tymofiy.dev.testsmatrapp.data.Repository
import tymofiy.dev.testsmatrapp.data.local.database.AppDatabase
import tymofiy.dev.testsmatrapp.data.remote.api.ApiFactory
import tymofiy.dev.testsmatrapp.utils.SharedPreferencesUtils

class MainApplication : Application() {

    lateinit var repository: Repository
    lateinit var shStorage: SharedPreferencesUtils

    override fun onCreate() {
        super.onCreate()
        val database: AppDatabase = AppDatabase.getDatabase(applicationContext)

        shStorage = SharedPreferencesUtils(applicationContext)

        repository = Repository(
            apiService = ApiFactory.apiService,
            gistDataDao = database.gistDao(),
            shStorage = shStorage
        )
    }
}
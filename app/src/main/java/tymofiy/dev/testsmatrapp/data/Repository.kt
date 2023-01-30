package tymofiy.dev.testsmatrapp.data

import android.content.SharedPreferences
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import tymofiy.dev.testsmatrapp.data.local.database.GistDataDao
import tymofiy.dev.testsmatrapp.data.local.model.GistEntity
import tymofiy.dev.testsmatrapp.data.remote.api.ApiService
import tymofiy.dev.testsmatrapp.data.remote.mapper.toEntity
import tymofiy.dev.testsmatrapp.data.remote.model.ApiGistModel
import tymofiy.dev.testsmatrapp.domain.IRepository
import tymofiy.dev.testsmatrapp.utils.SharedPreferencesUtils

class Repository(
    val apiService: ApiService,
    private val gistDataDao: GistDataDao,
    private val shStorage: SharedPreferencesUtils
) : IRepository {

    override suspend fun loadGist() {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getGistInfo()
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!.toEntity()
                    insertGistData(data)
                    shStorage.getUserState() ?: let {
                        shStorage.saveUserState(data.userStatus)
                    }
                } else {
                    val defaultData = ApiGistModel(false).toEntity()
                    insertGistData(defaultData)
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    private suspend fun insertGistData(entity: GistEntity) =
        gistDataDao.insert(entity)

    fun getGistEntity(): GistEntity = gistDataDao.getEntities()

    fun flowGistEntity(): Flow<GistEntity> = gistDataDao.flowEntities()
}


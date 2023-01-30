package tymofiy.dev.testsmatrapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tymofiy.dev.testsmatrapp.data.local.model.GistEntity

@Dao
interface GistDataDao {
    @Query("SELECT * FROM gistData ORDER BY id")
    fun getEntities(): GistEntity

    @Query("SELECT * FROM gistData ORDER BY id")
    fun flowEntities(): Flow<GistEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: GistEntity)
}
package ca.qc.cgodin.miniprojet3mobilecegep.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavorisDao {
    @Query("SELECT * FROM favoris")
    suspend fun getAllFavoris(): Flow<List<Favoris>>

    @Insert
    suspend fun insertFavoris(favoris: Favoris)

    @Delete
    suspend fun deleteFavoris(favoris: Favoris)
}
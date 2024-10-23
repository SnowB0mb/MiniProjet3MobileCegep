package ca.qc.cgodin.miniprojet3mobilecegep.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavorisDao {
    @Query("SELECT * from favoris_table ORDER BY nom ASC")
    fun getFavoris(): LiveData<List<Favoris>>
    @Query("SELECT * FROM favoris_table WHERE id=(:id)")
    fun getFavoris(id: Int): LiveData<Favoris?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoris: Favoris)

    @Update
    fun updateFavoris(favoris: Favoris)

    @Query("DELETE FROM favoris_table")
    fun deleteAll()

    @Delete
    fun delete(favoris: Favoris)

}
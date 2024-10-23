package ca.qc.cgodin.miniprojet3mobilecegep.repository

import androidx.lifecycle.LiveData
import ca.qc.cgodin.miniprojet3mobilecegep.models.Favoris
import ca.qc.cgodin.miniprojet3mobilecegep.models.FavorisDao

class FavorisRepository(private val favorisDao: FavorisDao) {
    val allFavoris: LiveData<List<Favoris>> = favorisDao.getFavoris()
    suspend fun insert(student: Favoris) {
        favorisDao.insert(student)
    }

    suspend fun delete(student: Favoris) {
        favorisDao.delete(student)
    }

}
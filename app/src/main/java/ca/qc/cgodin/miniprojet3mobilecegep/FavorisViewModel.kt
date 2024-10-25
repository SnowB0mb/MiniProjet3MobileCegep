package ca.qc.cgodin.miniprojet3mobilecegep

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ca.qc.cgodin.miniprojet3mobilecegep.models.Favoris
import ca.qc.cgodin.miniprojet3mobilecegep.models.FavorisRoomDatabase
import ca.qc.cgodin.miniprojet3mobilecegep.repository.FavorisRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavorisViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FavorisRepository
    val allFavoris: LiveData<List<Favoris>>

    init {
        val favorisDao =
            FavorisRoomDatabase.getDatabase(application).favorisDao()
        repository = FavorisRepository(favorisDao)
        allFavoris = repository.allFavoris
    }

    fun insert(favoris: Favoris) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(favoris)
    }

    fun delete(favoris: Favoris) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(favoris)
    }

}
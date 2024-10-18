package ca.qc.cgodin.miniprojet3mobilecegep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import ca.qc.cgodin.miniprojet3mobilecegep.repository.SuccursalesRepository

class SuccursaleViewModelProviderFactory(private val succursalesRepository: SuccursalesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return if (modelClass.isAssignableFrom(SuccursaleViewModel::class.java)) {
            SuccursaleViewModel(succursalesRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
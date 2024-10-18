package ca.qc.cgodin.miniprojet3mobilecegep

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseOperation
import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseStudent
import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseSuccursaleBudget
import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseSuccursaleCompte
import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseSuccursaleListe
import ca.qc.cgodin.miniprojet3mobilecegep.repository.SuccursalesRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class SuccursaleViewModel (private val succursalesRepository: SuccursalesRepository): ViewModel() {
    val responseConnexion: MutableLiveData<Response<ResponseStudent>> = MutableLiveData()
    val responseEnregistrement: MutableLiveData<ResponseStudent> = MutableLiveData()
    val responseListSuccursale: MutableLiveData<ResponseSuccursaleListe> = MutableLiveData()
    val responseNbSuccursale: MutableLiveData<ResponseSuccursaleCompte> = MutableLiveData()
    val responseBudgetSuccursale: MutableLiveData<ResponseSuccursaleBudget> = MutableLiveData()
    val responseAjoutSuccursale: MutableLiveData<ResponseOperation> = MutableLiveData()
    val responseRetraitSuccursale: MutableLiveData<ResponseOperation> = MutableLiveData()
    val responseSuppressionSuccursale: MutableLiveData<ResponseOperation> = MutableLiveData()

    //Connexion d'un utilisateur

    //on veut pouvoir se connecter depuis la vue de connexion
    //une fonction publique qui appelle la fonction privée
    fun connectUser(strMat: String, strMdp: String) {
        Log.d("SuccursaleViewModel", "connectUser called with matricule: $strMat")
        connexion(strMat, strMdp)
    }

    private fun connexion(strMat: String, strMdp: String) = viewModelScope.launch {
        Log.d("SuccursaleViewModel", "Attempting to connect user with matricule: $strMat")
        try {
            val response = succursalesRepository.connexion(strMat, strMdp)
            Log.d("SuccursaleViewModel", "Received response: $response")
            responseConnexion.postValue(response)
        } catch (e: Exception) {
            Log.e("SuccursaleViewModel", "Error connecting user", e)
        }
    }
}
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
    val responseEnregistrement: MutableLiveData<Response<ResponseStudent>> = MutableLiveData()
    val responseListSuccursale: MutableLiveData<Response<ResponseSuccursaleListe>> = MutableLiveData()
    val responseNbSuccursale: MutableLiveData<Response<ResponseSuccursaleCompte>> = MutableLiveData()
    val responseBudgetSuccursale: MutableLiveData<Response<ResponseSuccursaleBudget>> = MutableLiveData()
    val responseAjoutSuccursale: MutableLiveData<Response<ResponseOperation>> = MutableLiveData()
    val responseRetraitSuccursale: MutableLiveData<Response<ResponseOperation>> = MutableLiveData()
    val responseSuppressionSuccursale: MutableLiveData<Response<ResponseOperation>> = MutableLiveData()

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
            Log.d("SuccursaleViewModel", "Response body: ${response.body()}")
            responseConnexion.postValue(response)
        } catch (e: Exception) {
            Log.e("SuccursaleViewModel", "Error connecting user", e)
        }
    }

    //Enregistrement d'un utilisateur
    fun registerUser(strMat: String, strMdp: String, strPrenom: String, strNom: String){
        Log.d("SuccursaleViewModel", "registerUser called with matricule: $strMat")
        enregistrement(strMat, strMdp, strNom, strPrenom)
    }

    private fun enregistrement(strMat: String, strMdp: String, strPrenom: String, strNom: String) = viewModelScope.launch{
        Log.d("SuccursaleViewModel", "Attempting to register user with matricule: $strMat")
        try {
            val response = succursalesRepository.enregistrement(strMat, strMdp, strNom, strPrenom)
            Log.d("SuccursaleViewModel", "Received response: $response")
            Log.d("SuccursaleViewModel", "Response body: ${response.body()}")
            responseEnregistrement.postValue(response)
        }catch (e: Exception){
            Log.e("SuccursaleViewModel", "Error registering user", e)
        }
    }

    //récupérer la liste des succursales pour le compte qui est connecté
    fun getListSuccursale(strMat: String){
        Log.d("SuccursaleViewModel", "getListSuccursale called with Matricule: $strMat")
        listSuccursale(strMat)
    }

    private fun listSuccursale(strMat: String) = viewModelScope.launch{
        Log.d("SuccursaleViewModel", "Attempting to get list of succursale of matricule: $strMat")
        try {
            val response = succursalesRepository.getListSuccursale(strMat)
            Log.d("SuccursaleViewModel", "Received response: $response")
            Log.d("SuccursaleViewModel", "Response body: ${response.body()}")
            responseListSuccursale.postValue(response)
        }catch (e: Exception){
            Log.e("SuccursaleViewModel", "Error getting list of succursale", e)
        }
    }

    fun getBudgetSuccursale(strMat:String, strSearchSuccursale:String){
        Log.d("SuccursaleViewModel", "getBudgetSuccursale called")
        budgetSuccursale(strMat, strSearchSuccursale)
    }
    private fun budgetSuccursale(strMat:String, strSearchSuccursale:String) = viewModelScope.launch{
        Log.d("SuccursaleViewModel", "Attempting to get budget of succursale: $strSearchSuccursale")
        try {
            val response = succursalesRepository.getBudgetSuccursale(strMat, strSearchSuccursale)
            Log.d("SuccursaleViewModel", "Received response: $response")
            Log.d("SuccursaleViewModel", "Response body: ${response.body()}")
            responseBudgetSuccursale.postValue(response)
        }catch (e: Exception){
            Log.e("SuccursaleViewModel", "Error getting budget of succursale", e)
        }
    }

    fun getNbSuccursales(strMat:String){
        Log.d("SuccursaleViewModel", "nbSuccursale called")
        nbSuccursales(strMat)
    }

    private fun nbSuccursales(strMat:String) = viewModelScope.launch{
        Log.d("SuccursaleViewModel", "Attempting to get number of succursales: $strMat")
        try {
            val response = succursalesRepository.getNbSuccursale(strMat)
            Log.d("SuccursaleViewModel", "Received response: $response")
            Log.d("SuccursaleViewModel", "Response body: ${response.body()}")
            responseNbSuccursale.postValue(response)
        }catch (e: Exception){
            Log.e("SuccursaleViewModel", "Error getting number of succursales", e)
        }
    }
}
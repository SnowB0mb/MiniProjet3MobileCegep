package ca.qc.cgodin.miniprojet3mobilecegep.repository

import ca.qc.cgodin.miniprojet3mobilecegep.network.RetrofitInstance

class SuccursalesRepository() {
    suspend fun connexion(matricule: String, mdp: String) = RetrofitInstance.retrofitService.connexion(matricule, mdp)
    suspend fun enregistrement(matricule: String, mdp: String, nom: String, prenom: String) = RetrofitInstance.retrofitService.enregistrement(matricule, mdp, nom, prenom)
    suspend fun getListSuccursale(aut: String) = RetrofitInstance.retrofitService.getListSuccursale(aut)
    suspend fun getNbSuccursale(aut: String) = RetrofitInstance.retrofitService.getNbSuccursale(aut)
    suspend fun getBudgetSuccursale(aut: String, ville: String) = RetrofitInstance.retrofitService.getBudgetSuccursale(aut, ville)
    suspend fun ajoutSuccursale(aut: String, ville: String, budget: String) = RetrofitInstance.retrofitService.ajoutSuccursale(aut, ville, budget)
    suspend fun retraitSuccursale(aut: String, ville: String) = RetrofitInstance.retrofitService.retraitSuccursale(aut, ville)
    suspend fun suppressionSuccursale(aut: String) = RetrofitInstance.retrofitService.suppressionSuccursale(aut)
}
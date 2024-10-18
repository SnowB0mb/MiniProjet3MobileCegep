package ca.qc.cgodin.miniprojet3mobilecegep.network

import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseOperation
import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseStudent
import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseSuccursaleBudget
import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseSuccursaleCompte
import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseSuccursaleListe
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.Query

interface SuccursalesApiService {
    @POST("students/Connexion")
    suspend fun connexion(
        @Query("Mat") matricule: String,
        @Query("MDP") mdp: String,
    ): Response<ResponseStudent>

    @POST("/student/Enregistrement")
    suspend fun enregistrement(
        @Query("Mat") matricule: String,
        @Query("MDP") mdp: String,
        @Query("Nom") nom: String,
        @Query("Prenom") prenom: String,
    ): Response<ResponseStudent>

    @POST("/succursales/Succursale-Liste")
    suspend fun getListSuccursale(
        @Query("Aut") aut: String,
    ): Response<ResponseSuccursaleListe>

    @POST("/succursales/Succursale-Compte")
    suspend fun getNbSuccursale(
        @Query("Aut") aut: String,
    ): Response<ResponseSuccursaleCompte>

    @POST("/succursales/Succursale-Budget")
    suspend fun getBudgetSuccursale(
        @Query("Aut") aut: String,
        @Query("Ville") ville: String,
    ): Response<ResponseSuccursaleBudget>

    @POST("/succursales/Succursale-Ajout")
    suspend fun ajoutSuccursale(
        @Query("Aut") aut: String,
        @Query("Ville") ville: String,
        @Query("Budget") budget: String,
    ): Response<ResponseOperation>

    @DELETE("/succursales/Succursale-Retrait")
    suspend fun retraitSuccursale(
        @Query("Aut") aut: String,
        @Query("Ville") ville: String,
    ): Response<ResponseOperation>

    @DELETE("/succursales/Succursale-Suppression")
    suspend fun suppressionSuccursale(
        @Query("Aut") aut: String,
    ): Response<ResponseOperation>
}
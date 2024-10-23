package ca.qc.cgodin.miniprojet3mobilecegep.network

import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseOperation
import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseStudent
import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseSuccursaleBudget
import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseSuccursaleCompte
import ca.qc.cgodin.miniprojet3mobilecegep.models.ResponseSuccursaleListe
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Query

interface SuccursalesApiService {
    @FormUrlEncoded
    @POST("/students/Connexion")
    suspend fun connexion(
        @Field("Mat") matricule: String,
        @Field("MDP") mdp: String,
    ): Response<ResponseStudent>

    @FormUrlEncoded
    @POST("/students/Enregistrement")
    suspend fun enregistrement(
        @Field("Mat") matricule: String,
        @Field("MDP") mdp: String,
        @Field("Nom") nom: String,
        @Field("Prenom") prenom: String,
    ): Response<ResponseStudent>

    @FormUrlEncoded
    @POST("/succursales/Succursale-Liste")
    suspend fun getListSuccursale(
        @Field("Aut") aut: String,
    ): Response<ResponseSuccursaleListe>

    @FormUrlEncoded
    @POST("/succursales/Succursale-Compte")
    suspend fun getNbSuccursale(
        @Field("Aut") aut: String,
    ): Response<ResponseSuccursaleCompte>

    @FormUrlEncoded
    @POST("/succursales/Succursale-Budget")
    suspend fun getBudgetSuccursale(
        @Field("Aut") aut: String,
        @Field("Ville") ville: String,
    ): Response<ResponseSuccursaleBudget>

    @FormUrlEncoded
    @POST("/succursales/Succursale-Ajout")
    suspend fun ajoutSuccursale(
        @Field("Aut") aut: String,
        @Field("Ville") ville: String,
        @Field("Budget") budget: String,
    ): Response<ResponseOperation>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/succursales/Succursale-Retrait", hasBody = true)
    suspend fun retraitSuccursale(
        @Field("Aut") aut: String,
        @Field("Ville") ville: String,
    ): Response<ResponseOperation>

    @HTTP(method = "DELETE", path = "/succursales/Succursale-Suppression", hasBody = true)
    suspend fun suppressionSuccursale(
        @Query("Aut") aut: String,
    ): Response<ResponseOperation>
}
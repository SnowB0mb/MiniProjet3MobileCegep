package ca.qc.cgodin.miniprojet3mobilecegep.models


import com.google.gson.annotations.SerializedName

data class ResponseSuccursaleCompte(
    @SerializedName("nbSuccursales")
    val nbSuccursales: String,
    @SerializedName("statut")
    val statut: String
)
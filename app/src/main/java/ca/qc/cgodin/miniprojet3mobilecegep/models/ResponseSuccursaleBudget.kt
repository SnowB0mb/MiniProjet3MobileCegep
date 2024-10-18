package ca.qc.cgodin.miniprojet3mobilecegep.models


import com.google.gson.annotations.SerializedName

data class ResponseSuccursaleBudget(
    @SerializedName("statut")
    val statut: String,
    @SerializedName("succursale")
    val succursale: Succursale
)
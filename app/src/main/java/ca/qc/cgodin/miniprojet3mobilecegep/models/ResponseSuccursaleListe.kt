package ca.qc.cgodin.miniprojet3mobilecegep.models


import com.google.gson.annotations.SerializedName

data class ResponseSuccursaleListe(
    @SerializedName("statut")
    val statut: String,
    @SerializedName("succursales")
    val succursales: List<Succursale>
)
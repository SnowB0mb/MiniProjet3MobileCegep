package ca.qc.cgodin.miniprojet3mobilecegep.models


import com.google.gson.annotations.SerializedName

data class ResponseOperation(
    @SerializedName("statut")
    val statut: String
)
package ca.qc.cgodin.miniprojet3mobilecegep.models


import com.google.gson.annotations.SerializedName

data class Succursale(
    @SerializedName("AccessMDP")
    val accessMDP: Int,
    @SerializedName("Budget")
    val budget: Long,
    @SerializedName("_id")
    val id: String,
    @SerializedName("__v")
    val v: Int,
    @SerializedName("Ville")
    val ville: String
)
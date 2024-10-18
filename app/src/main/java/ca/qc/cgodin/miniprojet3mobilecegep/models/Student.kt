package ca.qc.cgodin.miniprojet3mobilecegep.models


import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("Matricule")
    val matricule: Int,
    @SerializedName("Nom")
    val nom: String,
    @SerializedName("Prenom")
    val prenom: String,
    @SerializedName("token")
    val token: String
)
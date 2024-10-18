package ca.qc.cgodin.miniprojet3mobilecegep.models


import com.google.gson.annotations.SerializedName

data class ResponseStudent(
    @SerializedName("statut")
    val statut: String,
    @SerializedName("student")
    val student: Student?,
    @SerializedName("error")
    val error: String?
)
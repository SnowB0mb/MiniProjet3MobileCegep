package ca.qc.cgodin.miniprojet3mobilecegep.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoris")
data class Favoris(
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
    val succursaleVille: String,
    val succursaleBudget: Double?

)
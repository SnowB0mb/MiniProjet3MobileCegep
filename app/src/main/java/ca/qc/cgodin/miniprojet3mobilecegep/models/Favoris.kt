package ca.qc.cgodin.miniprojet3mobilecegep.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoris_table")
data class Favoris(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id:Int = 0,
    @ColumnInfo(name = "Nom")
    val nom: String,
    @ColumnInfo(name = "Budget")
    val budget: Long,

)
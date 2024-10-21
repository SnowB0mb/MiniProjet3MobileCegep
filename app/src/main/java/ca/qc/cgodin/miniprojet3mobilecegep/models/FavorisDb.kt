package ca.qc.cgodin.miniprojet3mobilecegep.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favoris::class], version = 1, exportSchema = false)
abstract class FavorisDb : RoomDatabase() {
    abstract fun favorisDao(): FavorisDao

    companion object {
        @Volatile
        private var INSTANCE: FavorisDb? = null

        fun getDatabase(context: Context): FavorisDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavorisDb::class.java,
                    "favoris_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
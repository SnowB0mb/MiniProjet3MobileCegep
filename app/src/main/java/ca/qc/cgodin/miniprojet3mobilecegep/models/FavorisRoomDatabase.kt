package ca.qc.cgodin.miniprojet3mobilecegep.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Favoris::class), version = 1, exportSchema = false)
abstract class FavorisRoomDatabase: RoomDatabase() {
    abstract fun favorisDao(): FavorisDao

    companion object{
        @Volatile
        private var INSTANCE: FavorisRoomDatabase? = null

        fun getDatabase(context: Context): FavorisRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
            {
                return tempInstance
            }
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                FavorisRoomDatabase::class.java,
                "favoris_database"
            ).build()
            return INSTANCE as FavorisRoomDatabase
        }
    }
}
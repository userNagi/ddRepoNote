package com.nagi.ddreponote.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nagi.ddreponote.data.NoteList.Note
import com.nagi.ddreponote.data.NoteList.NoteDao

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: AppDatabase? = null
        private val instanceLock = Any()
        fun getInstance(context: Context): AppDatabase {
            synchronized(instanceLock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "DDRepoNote"
                    ).build()
                }
            }
            return instance!!
        }

        fun getInstance() = instance!!
    }
}
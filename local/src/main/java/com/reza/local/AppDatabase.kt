package com.reza.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.reza.local.converter.Converters
import com.reza.local.dao.MemberDao
import com.reza.local.model.MemberEntity

@Database(entities = [(MemberEntity::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao

    companion object {
        fun create(context: Context): AppDatabase {

            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "community-db"
            ).build()
        }
    }
}
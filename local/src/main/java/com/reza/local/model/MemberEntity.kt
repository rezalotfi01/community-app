package com.reza.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.reza.local.converter.Converters


@Entity(tableName = "Members")
data class MemberEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "firstName")
    val firstName: String,

    @ColumnInfo(name = "learns")
    @TypeConverters(Converters::class)
    val learns: List<String>,

    @ColumnInfo(name = "natives")
    @TypeConverters(Converters::class)
    val natives: List<String>,

    @ColumnInfo(name = "pictureUrl")
    val pictureUrl: String,

    @ColumnInfo(name = "referenceCnt")
    val referenceCnt: Int,

    @ColumnInfo(name = "topic")
    val topic: String,

    @ColumnInfo(name = "isLiked")
    val isLiked: Boolean,

    @ColumnInfo(name = "uniqueStrId")
    val uniqueStrId: String
)
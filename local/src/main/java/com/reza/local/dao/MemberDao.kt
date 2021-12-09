package com.reza.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reza.local.model.MemberEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {

    @Query("SELECT * FROM Members")
    fun getMembers(): Flow<List<MemberEntity>>

    @Query("SELECT * FROM Members WHERE isLiked = :liked")
    fun getMembersByLike(liked: Boolean): Flow<List<MemberEntity>>

    @Query("SELECT * FROM Members WHERE uniqueStrId = :uniqueStrId")
    fun getMemberByUniqueStrId(uniqueStrId: String): Flow<MemberEntity>

    @Query("DELETE FROM Members")
    suspend fun clearMembersTable()

    @Query("UPDATE Members SET isLiked = :liked WHERE uniqueStrId = :uniqueStrId")
    suspend fun setMemberLiked(uniqueStrId: String, liked: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(memberEntity: MemberEntity)
}
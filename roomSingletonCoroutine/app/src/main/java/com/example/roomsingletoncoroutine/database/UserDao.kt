package com.example.roomsingletoncoroutine.database

import androidx.room.*

@Dao
interface UserDao {
    @Query("Select * from User")
    fun getAll(): List<User>

    @Insert
    suspend fun insert(user: User)

    @Query("Delete from User where name = :name")
    fun deleteUserByName(name: String)
}
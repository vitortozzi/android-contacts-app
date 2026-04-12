package com.example.simplecontacts.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.simplecontacts.domain.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact")
    fun getAll(): Flow<List<Contact>>

    @Insert
    suspend fun insert(contact: Contact)
}
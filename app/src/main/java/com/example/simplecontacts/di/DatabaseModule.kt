package com.example.simplecontacts.di

import android.content.Context
import androidx.room.Room
import com.example.simplecontacts.data.ContactDao
import com.example.simplecontacts.data.ContactDatabase
import com.example.simplecontacts.data.ContactRepositoryImpl
import com.example.simplecontacts.domain.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ContactDatabase {
        return Room.databaseBuilder(
            context,
            ContactDatabase::class.java,
            "contacts_db"
        ).build()
    }

    @Provides
    fun provideContactDao(database: ContactDatabase): ContactDao {
        return database.contactDao()
    }

    @Provides
    @Singleton
    fun provideRepository(dao: ContactDao): ContactRepository {
        return ContactRepositoryImpl(dao)
    }

}
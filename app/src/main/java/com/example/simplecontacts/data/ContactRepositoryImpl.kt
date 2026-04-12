package com.example.simplecontacts.data

import com.example.simplecontacts.domain.Contact
import com.example.simplecontacts.domain.ContactRepository
import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl(
    private val dao: ContactDao
) : ContactRepository {

    override fun getContacts(): Flow<List<Contact>> {
        return dao.getAll()
    }

    override suspend fun addContact(contact: Contact) {
        dao.insert(contact)
    }
}
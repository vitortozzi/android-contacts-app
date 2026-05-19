package com.example.simplecontacts.ui.contacts.list

import com.example.simplecontacts.domain.Contact

sealed interface ContactsEvent {
    data class NavigationToDetail(val contact: Contact): ContactsEvent
}
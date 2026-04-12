package com.example.simplecontacts.ui.contacts.list

import com.example.simplecontacts.domain.Contact

data class UiState(
    val contacts: List<Contact> = listOf(),
    val showDialog: Boolean = false,
)
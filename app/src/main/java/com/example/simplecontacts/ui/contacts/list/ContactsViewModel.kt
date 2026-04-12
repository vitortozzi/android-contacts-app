package com.example.simplecontacts.ui.contacts.list

import androidx.lifecycle.ViewModel
import com.example.simplecontacts.domain.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactsViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(
        UiState(
            listOf(
                Contact("Vitor", "(41) 99232-0199"),
                Contact("Pai", "(11) 99885-6531"),
                Contact("Lidia", "(11) 97119-0100"),
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    fun addContact(contact: Contact) {
        _uiState.value = _uiState.value.copy(
            showDialog = false,
            contacts = _uiState.value.contacts + contact
        )
    }


    fun onClickAdd() {
        _uiState.value = _uiState.value.copy(
            showDialog = true,
        )
    }

    fun dismissDialog() {
        _uiState.value = _uiState.value.copy(
            showDialog = false,
        )
    }


}
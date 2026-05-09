package com.example.simplecontacts

import app.cash.turbine.test
import com.example.simplecontacts.domain.Contact
import com.example.simplecontacts.domain.ContactRepository
import com.example.simplecontacts.ui.contacts.list.ContactsViewModel
import com.example.simplecontacts.ui.contacts.list.UiState
import io.mockk.coJustRun
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContactsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = mockk<ContactRepository>()
    private lateinit var viewModel: ContactsViewModel

    private val contactsFlow = MutableStateFlow<List<Contact>>(emptyList())

    @Before
    fun setup() {
        every { repository.getContacts() } returns contactsFlow
        coJustRun { repository.addContact(any()) }
        viewModel = ContactsViewModel(
            repository
        )
    }

    @Test
    fun `when addContact is called then the contact should show on uiState list`() = runTest {

        val newContact = Contact(
            1,
            name = "Vitor",
            phoneNumber = "12344-5678"
        )

        viewModel.uiState.test {

            assertEquals(UiState(listOf(), showDialog = false), awaitItem())

            viewModel.addContact(
                newContact
            )

            contactsFlow.emit(listOf(newContact))

            assertEquals(
                UiState(
                    listOf(newContact),
                    false,
                ),
                awaitItem()
            )
        }
    }

    @Test
    fun `when onClickAdd is called the should update uiState with show dialog true`() = runTest {

        viewModel.uiState.test {
            assertEquals(UiState(listOf(), showDialog = false), awaitItem())
            viewModel.onClickAdd()
            assertEquals(UiState(listOf(), true), awaitItem())
        }
    }

    @Test
    fun `when dismissDialog is called then should emit state with showDialog false`() = runTest {
        viewModel.uiState.test {
            assertEquals(UiState(listOf(), showDialog = false), awaitItem())
            viewModel.onClickAdd()
            assertEquals(UiState(listOf(), true), awaitItem())
            viewModel.dismissDialog()
            assertEquals(UiState(listOf(), showDialog = false), awaitItem())
        }
    }
}
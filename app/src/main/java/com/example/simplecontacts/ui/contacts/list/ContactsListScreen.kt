package com.example.simplecontacts.ui.contacts.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplecontacts.domain.Contact
import com.example.simplecontacts.ui.theme.SimpleContactsTheme

@Composable
fun MainScreen(
    uiState: UiState,
    onFabClick: () -> Unit,
    onDismiss: () -> Unit,
    onAddContact: (Contact) -> Unit,
    onContactClick: (Contact) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                {
                    onFabClick()
                },
                shape = CircleShape,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add contact"
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            ContactList(
                contacts = uiState.contacts,
                onContactClick = onContactClick
            )
            ContactDialog(
                showDialog = uiState.showDialog,
                onDismiss = onDismiss,
                onAddContact = {
                    onAddContact(it)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onAddContact: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {
    var contactName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    if (showDialog) {
        BasicAlertDialog(
            modifier = modifier,
            onDismissRequest = { onDismiss() }
        ) {
            Surface(
                shape = MaterialTheme.shapes.large,
                tonalElevation = 6.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Please, inform the contact info below:")
                    TextField(
                        value = contactName,
                        placeholder = {
                            Text("Name")
                        },
                        onValueChange = {
                            contactName = it
                        }
                    )
                    TextField(
                        value = phoneNumber,
                        placeholder = {
                            Text("Phone Number")
                        },
                        onValueChange = {
                            phoneNumber = it
                        }
                    )
                    Button(
                        onClick = {
                            onAddContact(
                                Contact(
                                    name = contactName,
                                    phoneNumber = phoneNumber
                                )
                            )
                            contactName = ""
                            phoneNumber = ""
                            onDismiss()
                        }
                    ) {
                        Text("Add Contact")
                    }
                }
            }
        }
    }

}

@Composable
fun ContactList(
    contacts: List<Contact>,
    onContactClick: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(contacts) { contact ->
            ContactItem(
                contact,
                onContactClick = onContactClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactListPreview() {
    SimpleContactsTheme {
        ContactList(
            listOf(),
            onContactClick = {}
        )
    }
}

@Composable
fun ContactItem(
    contact: Contact,
    onContactClick: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    onContactClick(contact)
                }
            )
            .padding(bottom = 8.dp),
    ) {
        Text(
            text = contact.name,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = contact.phoneNumber,
            style = MaterialTheme.typography.labelSmall
        )
    }

}
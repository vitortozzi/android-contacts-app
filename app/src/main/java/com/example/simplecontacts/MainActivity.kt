package com.example.simplecontacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.simplecontacts.domain.Contact
import com.example.simplecontacts.ui.navigation.ContactDetailRoute
import com.example.simplecontacts.ui.navigation.ContactListRoute
import com.example.simplecontacts.ui.contacts.details.ContactDetailScreen
import com.example.simplecontacts.ui.contacts.list.ContactsEvent
import com.example.simplecontacts.ui.contacts.list.ContactsViewModel
import com.example.simplecontacts.ui.contacts.list.MainScreen
import com.example.simplecontacts.ui.theme.SimpleContactsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: ContactsViewModel = viewModel()
            val navController = rememberNavController()
            SimpleContactsTheme {

                LaunchedEffect(Unit) {
                    viewModel.events.collect {
                        when (it) {
                            is ContactsEvent.NavigationToDetail -> {
                                navController.navigate(ContactDetailRoute(it.contact.name, it.contact.phoneNumber))
                            }
                        }
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = ContactListRoute,
                ) {
                    composable<ContactListRoute> {
                        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                        MainScreen(
                            uiState = uiState,
                            onFabClick = {
                                viewModel.onClickAdd()
                            },
                            onDismiss = {
                                viewModel.dismissDialog()
                                        },
                            onAddContact = {
                                viewModel.addContact(it)
                            },
                            onContactClick = {
                                viewModel.onTapContact(it)
                            }
                        )
                    }
                    composable<ContactDetailRoute> {
                        val route = it.toRoute<ContactDetailRoute>()
                        ContactDetailScreen(
                            Contact(
                                name = route.name,
                                phoneNumber = route.phoneNumber
                            ),
                            onClickBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
package com.example.simplecontacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplecontacts.domain.Contact
import com.example.simplecontacts.ui.contacts.details.ContactDetailScreen
import com.example.simplecontacts.ui.contacts.list.ContactsViewModel
import com.example.simplecontacts.ui.contacts.list.MainScreen
import com.example.simplecontacts.ui.theme.SimpleContactsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: ContactsViewModel = viewModel()
            val navController = rememberNavController()
            SimpleContactsTheme {
                NavHost(
                    navController = navController,
                    startDestination = "list"
                ) {
                    composable(
                        route = "list"
                    ) {
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
                                navController.navigate("details/${it.name}/${it.phoneNumber}")
                            }
                        )
                    }
                    composable(
                        "details/{contactName}/{phoneNumber}"
                    ) {
                        val contactName = it.arguments?.getString("contactName") ?: ""
                        val phoneNumber = it.arguments?.getString("phoneNumber") ?: ""
                        ContactDetailScreen(
                            Contact(
                                name = contactName,
                                phoneNumber = phoneNumber
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
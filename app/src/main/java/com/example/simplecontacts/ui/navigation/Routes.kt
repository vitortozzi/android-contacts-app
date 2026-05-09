package com.example.simplecontacts.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object ContactListRoute

@Serializable
data class ContactDetailRoute(
    val name: String,
    val phoneNumber: String
)
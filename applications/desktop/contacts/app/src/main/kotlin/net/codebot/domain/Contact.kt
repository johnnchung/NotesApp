package net.codebot.domain

import kotlinx.serialization.Serializable

@Serializable
data class Contact(var name: String, var phone: String, var email: String)
package com.dev.andre.mysql.model

import kotlinx.serialization.Serializable


@Serializable
data class UserModel(
    val password: String? = null,
    val id: Int? = null,
    val uuid: String? = null,
    val email: String? = null
)

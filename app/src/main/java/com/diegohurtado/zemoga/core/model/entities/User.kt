package com.diegohurtado.zemoga.core.model.entities

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
) {
    constructor() : this(0, "", "", "", "", "")
}

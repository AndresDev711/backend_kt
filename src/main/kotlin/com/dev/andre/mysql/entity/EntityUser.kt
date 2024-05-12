package com.dev.andre.mysql.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar


object EntityUser : Table<Nothing>(tableName = "auth") {
    val id = int(name = "id").primaryKey()
    val uuid = varchar(name = "uuid")
    val email = varchar(name = "email")
    val password = varchar(name = "password")
    val roleId = int(name = "role_id")
}


package com.example.mongotreenodes.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class UserEntity(

    @Id
    val id: String? = null,
    val name: String,
    val nodes: List<String> = emptyList()
)

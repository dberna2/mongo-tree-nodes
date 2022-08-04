package com.example.mongotreenodes.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class NodeEntity(

    @Id
    val id: String? = null,
    val title: String,
    //val hasPermission: Boolean = false,
    val parents: List<String> = listOf(),
    val ancestors: List<String> = listOf(),
    val children: List<String> = listOf()
)

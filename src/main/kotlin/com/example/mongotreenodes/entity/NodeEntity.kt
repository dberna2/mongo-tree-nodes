package com.example.mongotreenodes.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class NodeEntity(

    @Id
    val id: String? = null,
    val title: String,
    //val hasPermission: Boolean = false,
    val parents: List<String>? = emptyList(),
    val ancestors: List<String>? = emptyList(),
    val children: List<String>? = emptyList()
)

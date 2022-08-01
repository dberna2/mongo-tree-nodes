package com.example.mongotreenodes.repository

import com.example.mongotreenodes.entity.NodeEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface NodeRepository : MongoRepository<NodeEntity, String> {
    
}
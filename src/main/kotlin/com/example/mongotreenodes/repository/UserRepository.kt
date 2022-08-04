package com.example.mongotreenodes.repository

import com.example.mongotreenodes.entity.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<UserEntity, String>

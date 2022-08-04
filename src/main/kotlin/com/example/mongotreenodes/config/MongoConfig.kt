package com.example.mongotreenodes.config

import com.example.mongotreenodes.entity.NodeEntity
import com.example.mongotreenodes.entity.UserEntity
import com.example.mongotreenodes.repository.NodeRepository
import com.example.mongotreenodes.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MongoConfig(val nodeRepository: NodeRepository, val userRepository: UserRepository) {

    val createdNodes: MutableList<String> = mutableListOf()

    @Bean
    fun init() = CommandLineRunner {

        val isEmpty = nodeRepository.findAll().isEmpty()
        if (isEmpty) {
            val startTime = System.currentTimeMillis()

            makeNode(null, 0, 1000)

            val endTiempo = System.currentTimeMillis() - startTime
            println("Duration was: $endTiempo ms")
        }

        val isEmptyList = userRepository.findAll().isEmpty()

        if (isEmptyList) {

            val nodes = getRangeByCreatedNodes(createdNodes)


            val user = UserEntity(name = "John", nodes = nodes)

            userRepository.save(user)
        }
    }

    private fun getRangeByCreatedNodes(createdNodes: MutableList<String>): List<String> = createdNodes.asSequence().shuffled().take(1).toList()

    fun makeNode(parentNode: NodeEntity?, current: Int, limit: Int) {

        if (current >= limit) {
            return
        }

        val parent = if (parentNode != null) {
            arrayListOf(parentNode.title)
        } else {
            arrayListOf()
        }

        val ancestors = createdNodes
            .filter { it != parentNode?.title }


        val title = "Node-" + current.plus(1)

        val entity = if (parentNode == null) {
            NodeEntity(title = title)
        } else if (parentNode.parents.isEmpty()) {
            NodeEntity(title = title, parents = parent)
        } else {
            NodeEntity(title = title, parents = parent, ancestors = ancestors)
        }

        createdNodes.add(entity.title)

        nodeRepository.save(entity)

        makeNode(entity, current.plus(1), limit)

    }
}

package com.example.mongotreenodes.service

import com.example.mongotreenodes.entity.NodeEntity
import com.example.mongotreenodes.entity.UserEntity
import org.apache.commons.collections4.ListUtils
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Service

@Service
class NodeServiceImpl(val mongoTemplate: MongoTemplate) :
    NodeService {

    override fun hasNodePermissions(id: String, nodeName: String): Boolean {

        val user: UserEntity = mongoTemplate.findById(id, UserEntity::class.java)
            ?: throw RuntimeException("User does not exist.")

        if (user.nodes.contains(nodeName)) {
            return true
        }

        val userQueryNode = Query().addCriteria(where("title").isEqualTo(nodeName))

        val nodeEntity = mongoTemplate.findOne(userQueryNode, NodeEntity::class.java)
            ?: throw RuntimeException("Node does not exist.")

        val joinedNewList = ListUtils.union(nodeEntity.parents, nodeEntity.ancestors)

        val query = Query().addCriteria(buildCriteria("title", joinedNewList))

        val nodes: List<NodeEntity> = mongoTemplate.find(query, NodeEntity::class.java)

        return nodes.isNotEmpty()
    }

    private fun buildCriteria(field: String, values: List<String>): Criteria {
        return where(field).`in`(values)
    }
}
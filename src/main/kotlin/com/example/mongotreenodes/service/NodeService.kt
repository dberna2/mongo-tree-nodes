package com.example.mongotreenodes.service

interface NodeService {

    fun hasNodePermissions(id: String, nodeName: String): Boolean
}
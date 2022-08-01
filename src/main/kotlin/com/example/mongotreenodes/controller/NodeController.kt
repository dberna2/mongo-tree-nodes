package com.example.mongotreenodes.controller

import com.example.mongotreenodes.service.NodeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class NodeController(val service: NodeService) {


    @GetMapping("/{id}")
    fun hasPermissions(@PathVariable id: String, @RequestParam("node") nodeName: String): Boolean {
        val startTime = System.currentTimeMillis()

        val result = service.hasNodePermissions(id, nodeName);

        val endTiempo = System.currentTimeMillis() - startTime
        println("Request duration: $endTiempo ms")

        return result
    }
}
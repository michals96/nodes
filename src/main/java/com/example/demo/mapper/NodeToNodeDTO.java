package com.example.demo.mapper;

import com.example.demo.model.Node;
import com.example.demo.model.dto.NodeDTO;

public class NodeToNodeDTO {
    public static NodeDTO map(Node node) {
        return NodeDTO.builder()
            .name(node.getName())
            .value(node.getValue())
            .build();
    }
}

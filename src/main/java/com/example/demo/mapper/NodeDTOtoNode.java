package com.example.demo.mapper;

import com.example.demo.model.Node;
import com.example.demo.model.dto.NodeDTO;

public class NodeDTOtoNode {
    public static Node map(NodeDTO node) {
        return Node.builder()
            .name(node.getName())
            .value(node.getValue())
            .build();
    }
}

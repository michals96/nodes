package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.mapper.NodeCommandToNode;
import com.example.demo.model.Node;
import com.example.demo.model.command.NodeCommand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class NodeService {
    private Map<String, Map> nodeMap = new HashMap<>();

    public Map<String, Map> getNodes(NodeCommand nodeCommand) {
        generateNodes(NodeCommandToNode.map(nodeCommand));
        return nodeMap;
    }

    public void generateNodes(Node node) {
        this.nodeMap.clear();
        this.nodeMap.put(node.getName(), generateNodeMap(node));
    }

    public Map generateNodeMap(Node node) {
        return node.getNodeList()
            .stream()
            .collect(Collectors.toMap(Node::getName, Node::getValue));
    }
}

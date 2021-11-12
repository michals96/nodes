package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        generateNode(NodeCommandToNode.map(nodeCommand));
        return nodeMap;
    }

    public void generateNode(Node node) {
        this.nodeMap.put(node.getName(), Map.of("b", 1));
    }
}

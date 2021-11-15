package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    private Map<String, Map> nodeMap = new TreeMap<>();

    public Map<String, Map> getNodes(NodeCommand nodeCommand) {
        generateNodes(NodeCommandToNode.map(nodeCommand));
        return nodeMap;
    }

    public Map<String, Map> getReducedNodes(NodeCommand nodeCommand) {
        removeNodes(NodeCommandToNode.map(nodeCommand));
        return nodeMap;
    }
    public void removeNodes(List<Node> nodes) {
        // do something
    }

    public void generateNodes(List<Node> nodes) {
        this.nodeMap.clear();
        Map<String, Map> mapPtr = new TreeMap<>();
        Map<String, Map> insertMap = new TreeMap<>();
        mapPtr = nodeMap;
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).getValue() == null) {
                if(nodes.get(i+1).getValue()==null) {
                    insertMap = new HashMap<>( Map.of(nodes.get(i+1).getName(), new HashMap<>(Map.of(nodes.get(i+1).getName(), ""))));
                    mapPtr.put(nodes.get(i).getName(), insertMap);
                    mapPtr = insertMap;
                } else {
                    mapPtr.put(nodes.get(i).getName(), Map.of(nodes.get(i+1).getName(), nodes.get(i+1).getValue()));
                }
            }
        }
    }
}

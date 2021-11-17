package com.example.demo.service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import com.example.demo.mapper.NodeCommandToNode;
import com.example.demo.mapper.NodeDTOtoNode;
import com.example.demo.mapper.NodeToNodeDTO;
import com.example.demo.model.Node;
import com.example.demo.model.command.NodeCommand;
import com.example.demo.repository.NodeDTORepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class NodeService {
    private final NodeDTORepository nodeDTORepository;
    private Map<String, Map> nodeMap = new TreeMap<>();
    private List<Node> nodeList = new ArrayList<>();

    public Map<String, Map> getNodes(NodeCommand nodeCommand) {
        generateNodes(NodeCommandToNode.map(nodeCommand));
        return nodeMap;
    }

    private void saveNodesToDb(NodeCommand nodeCommand) {
        nodeDTORepository.deleteAll();
        NodeCommandToNode.map(nodeCommand).stream().map(val -> NodeToNodeDTO.map(val)).forEach(nodeDTORepository::save);
    }

    public Map<String, Map> getNodesFromDb(NodeCommand nodeCommand) {
        saveNodesToDb(nodeCommand);
        List<Node> nodes = nodeDTORepository.findAll()
            .stream().map(nodeDTO -> NodeDTOtoNode.map(nodeDTO)).collect(Collectors.toList());
        return generateNodesFromDb(nodes);
    }

    private Map<String, Map> generateNodesFromDb(List<Node> nodes) {
        Map<String, Map> nodeMap = new TreeMap<>();
        Map<String, Map> mapPtr = new TreeMap<>();
        Map<String, Map> insertMap = new TreeMap<>();
        mapPtr = nodeMap;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getValue() == null) {
                if (nodes.get(i + 1).getValue() == null) {
                    insertMap = new HashMap<>(Map.of(nodes.get(i + 1).getName(), new HashMap<>(Map.of(nodes.get(i + 1).getName(), ""))));
                    mapPtr.put(nodes.get(i).getName(), insertMap);
                    mapPtr = insertMap;
                } else {
                    mapPtr.put(nodes.get(i).getName(), Map.of(nodes.get(i + 1).getName(), nodes.get(i + 1).getValue()));
                }
            }
        }
        return nodeMap;
    }

    private void generateNodes(List<Node> nodes) {
        this.nodeMap.clear();
        this.nodeList.clear();
        this.nodeList.addAll(nodes);

        Map<String, Map> mapPtr = new TreeMap<>();
        Map<String, Map> insertMap = new TreeMap<>();
        mapPtr = nodeMap;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getValue() == null) {
                if (nodes.get(i + 1).getValue() == null) {
                    insertMap = new HashMap<>(Map.of(nodes.get(i + 1).getName(), new HashMap<>(Map.of(nodes.get(i + 1).getName(), ""))));
                    mapPtr.put(nodes.get(i).getName(), insertMap);
                    mapPtr = insertMap;
                } else {
                    mapPtr.put(nodes.get(i).getName(), Map.of(nodes.get(i + 1).getName(), nodes.get(i + 1).getValue()));
                }
            }
        }
    }

    public Map<String, Map> getReducedNodes(NodeCommand nodeCommand) {
        removeNodes(NodeCommandToNode.map(nodeCommand));
        return nodeMap;
    }

    private void removeNodes(List<Node> nodes) {
        List<Node> filteredNodes = new CopyOnWriteArrayList<>();
        Integer val = 0;
        filteredNodes.addAll(this.nodeList);

        for (Node node : nodes) {
            for (Node n : filteredNodes) {
                if (node.getName().equals(n.getName()) && n.getValue() == null) {
                    filteredNodes.remove(n);
                } else if (node.getName().equals(n.getName()) && n.getValue() != null) {
                    val = n.getValue();
                    filteredNodes.remove(n);
                }
            }
        }

        if (val != 0) {
            filteredNodes.get(filteredNodes.size() - 1).setValue(val);
        }

        generateNodes(filteredNodes);
    }
}

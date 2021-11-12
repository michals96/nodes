package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Node;
import com.example.demo.model.command.NodeCommand;

public class NodeCommandToNode {
    public static String getNodeName(String nodePath) {
        String[] split = nodePath.split("/");
        return split[0];
    }

    public static Integer getNodeAmount(String nodePath){
        return nodePath.split("/").length;
    }

    public static List<Node> getNodeList(String nodePath, Integer val) {
        String[] split = nodePath.split("/");
        List<Node> nodeList = new ArrayList<>();
        for(int i=1; i< split.length-1;i++) {
            nodeList.add(new Node(split[i]));
        }

        nodeList.add(new Node(split[split.length-1], val));

        return nodeList;
    }

    public static Node map(NodeCommand nodeCommand) {

        if(getNodeAmount(nodeCommand.getPath()) > 1) {
            return Node.builder()
                .name(getNodeName(nodeCommand.getPath()))
                .nodeList(getNodeList(nodeCommand.getPath(), nodeCommand.getValue()))
                .build();
        }

        return Node.builder()
            .name(getNodeName(nodeCommand.getPath()))
            .build();
    }
}

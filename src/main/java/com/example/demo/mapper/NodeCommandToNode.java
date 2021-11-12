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

    public static Node map(NodeCommand nodeCommand) {
        List<Node> nodeList = new ArrayList<>();

        if(getNodeAmount(nodeCommand.getPath()) > 1) {
            nodeList.add(generateNode());
        }

        return Node.builder()
            .name(getNodeName(nodeCommand.getPath()))
            .build();
    }
}

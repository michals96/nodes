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

    public static Integer getNodeAmount(String nodePath) {
        return nodePath.split("/").length;
    }

    public static List<Node> getNodeList(String nodePath, Integer val) {
        String[] split = nodePath.split("/");
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < split.length - 1; i++) {
            nodeList.add(new Node(split[i]));
        }

        nodeList.add(new Node(split[split.length - 1], val));

        return nodeList;
    }

    public static List<Node> map(NodeCommand nodeCommand) {
        List<Node> nodeList = new ArrayList<>();

        if (getNodeAmount(nodeCommand.getPath()) > 1) {
            nodeList.addAll(getNodeList(nodeCommand.getPath(), nodeCommand.getValue()));
        } else {
            nodeList.add(new Node(getNodeName(nodeCommand.getPath()), nodeCommand.getValue()));
        }

        return nodeList;
    }
}

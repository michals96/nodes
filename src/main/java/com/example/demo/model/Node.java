package com.example.demo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Node {
    private String name;
    private Integer value;
    private List<Node> nodeList;

    public Node(final String s) {
        this.name = s;
    }

    public Node(final String s, final Integer val) {
        this.name = s;
        this.value = val;
    }
}

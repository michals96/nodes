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
}

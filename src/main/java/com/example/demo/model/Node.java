package com.example.demo.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class Node {
    @NonNull private final String name;
    private final Integer value;

    public Node(final String name) {
        this.name = name;
        this.value = null;
    }
}

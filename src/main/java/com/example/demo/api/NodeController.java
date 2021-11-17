package com.example.demo.api;

import java.util.Arrays;
import java.util.Map;

import com.example.demo.model.command.NodeCommand;
import com.example.demo.service.NodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/node")
@RequiredArgsConstructor
@Slf4j
public class NodeController {

    private final NodeService nodeService;
    private final Environment environment;

    @PutMapping
    public Map<String, Map> createStructure(@RequestBody NodeCommand nodeCommand) {

        if (Arrays.asList(environment.getActiveProfiles()).contains("db")) {
            return nodeService.getNodesFromDb(nodeCommand);
        }

        return nodeService.getNodes(nodeCommand);
    }

    @DeleteMapping
    public Map<String, Map> removeStructure(@RequestBody NodeCommand nodeCommand) {
        return nodeService.getReducedNodes(nodeCommand);
    }
}

package com.example.demo.api;

import java.util.Map;

import com.example.demo.model.command.NodeCommand;
import com.example.demo.service.NodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/node")
@RequiredArgsConstructor
@Slf4j
public class NodeController {

    private final NodeService nodeService;

    @PutMapping
    public Map<String, Map> createStructure(@RequestBody NodeCommand nodeCommand){
        return nodeService.getNodes(nodeCommand);
    }

    @DeleteMapping
    public void removeStructure(){
        System.out.println("");
    }
}

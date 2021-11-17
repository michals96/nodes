package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.dto.NodeDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeDTORepository extends CrudRepository<NodeDTO, Long> {
    List<NodeDTO> findAll();
    void deleteAll();
}

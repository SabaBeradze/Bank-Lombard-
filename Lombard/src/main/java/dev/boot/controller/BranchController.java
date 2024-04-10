package dev.boot.controller;


import dev.boot.dto.BranchDTO;
import dev.boot.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {
    private final BranchService service;

    @PostMapping("/add")
    public ResponseEntity<BranchDTO> save(@RequestBody BranchDTO entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
    }


    @GetMapping("/{id}")
    public BranchDTO getById(@PathVariable long id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    public List<BranchDTO> getAll() {
        return service.findAll();
    }
}

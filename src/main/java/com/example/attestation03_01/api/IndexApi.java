package com.example.attestation03_01.api;

import com.example.attestation03_01.dto.IndexDto;
import com.example.attestation03_01.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndexApi {
    @Autowired
    IndexService indexService;

    @GetMapping("stats")
    ResponseEntity<IndexDto> stats() {
        return ResponseEntity.ok(indexService.apiStats());
    }
}

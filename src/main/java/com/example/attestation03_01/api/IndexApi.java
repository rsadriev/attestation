package com.example.attestation03_01.api;

import com.example.attestation03_01.dto.IndexDto;
import com.example.attestation03_01.service.IndexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndexApi {
    @Autowired
    IndexService indexService;

    @Operation(summary = "Получить статистику приложения")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Получена статистика приложения",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = IndexDto.class)
                            )
                    )
            }
    )
    @GetMapping("stats")
    public ResponseEntity<IndexDto> stats() {
        return ResponseEntity.ok(indexService.apiStats());
    }
}

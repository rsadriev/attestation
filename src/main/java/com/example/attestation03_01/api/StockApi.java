package com.example.attestation03_01.api;

import com.example.attestation03_01.exception.ResourceNotFoundException;
import com.example.attestation03_01.model.Stock;
import com.example.attestation03_01.service.CustomerService;
import com.example.attestation03_01.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/stock")
public class StockApi {
    @Autowired
    StockService stockService;

    @Autowired
    CustomerService customerService;

    @Operation(summary = "Получение товаров доступных для покупки")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Получены товары доступные для покупки",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = Stock.class))
                                    )
                            }
                    )
            }
    )
    @GetMapping("active")
    ResponseEntity<Iterable<Stock>> getActiveStocks() {
        return ResponseEntity.ok(customerService.findActiveStocks());
    }

    @Operation(summary = "Получение всех товаров")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Получены товары",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = Stock.class))
                                    )
                            }
                    )
            }
    )
    @GetMapping()
    ResponseEntity<Iterable<Stock>> getStocks() {
        return ResponseEntity.ok(stockService.apiFindStocks());
    }

    @Operation(summary = "Получение товара по id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Получен товар",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Stock.class)
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "404", description = "Товар не найден")
            }
    )
    @GetMapping("{id}")
    ResponseEntity<Stock> getStock(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.findStockById(id).orElseThrow(ResourceNotFoundException::new));
    }


    @Operation(summary = "Добавление товара")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Товар добавлен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Stock.class)
                                    )
                            }
                    )
            }
    )
    @PostMapping("")
    ResponseEntity<Stock> createStock(@RequestBody Stock payload) {
        return ResponseEntity.ok(stockService.apiSaveStock(payload));
    }

    @Operation(summary = "Обновление товара")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Товар обновлен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Stock.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Товар не найден"
                    )
            }
    )
    @PutMapping("")
    ResponseEntity<Void> updateStock(@RequestBody Stock payload) {
        stockService.apiUpdateStock(payload);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Удаление товара")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Товар удален"
                    )
            }
    )
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.apiDeleteStock(id);

        return ResponseEntity.noContent().build();
    }
}

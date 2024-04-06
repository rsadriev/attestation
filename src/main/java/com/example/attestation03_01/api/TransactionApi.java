package com.example.attestation03_01.api;

import com.example.attestation03_01.dto.CustomerStockApiDto;
import com.example.attestation03_01.model.Customer;
import com.example.attestation03_01.service.CustomerService;
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
@RequestMapping("/api/customer")
public class TransactionApi {
    @Autowired
    CustomerService customerService;

    @Operation(summary = "Получение покупок пользователя")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Покупки получены",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = CustomerStockApiDto.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь не найден"
                    )
            }
    )
    @GetMapping("{id}/stock")
    ResponseEntity<Iterable<CustomerStockApiDto>> getStocks(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.apiFindStocksByCustomer(id));
    }

    @Operation(summary = "Получение покупки пользователя")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Покупка получены",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = CustomerStockApiDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь или покупка найдены"
                    )
            }
    )
    @GetMapping("{id}/stock/{tId}")
    ResponseEntity<CustomerStockApiDto> getStock(@PathVariable Long id, @PathVariable Long tId) {
        return ResponseEntity.ok(customerService.apiFindStocksByIdCustomer(id, tId));
    }

    @Operation(summary = "Добавление покупоки пользователю")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Покупка добавлена",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = CustomerStockApiDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь или покупка не найдены"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Недостаточно баланса у пользователя либо недостаточно единиц товара для покупки"
                    )
            }
    )
    @PostMapping("{id}/stock")
    ResponseEntity<CustomerStockApiDto> createTransaction(@PathVariable Long id, @RequestBody CustomerStockApiDto payload) {
        return ResponseEntity.ok(customerService.apiAddTransaction(id, payload));
    }

    @Operation(summary = "Удаление покупки пользователя")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Покупка удалена"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь или покупка не найдены"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Попытка удалить покупку не принадлежащую пользователю"
                    )
            }
    )
    @DeleteMapping("{id}/stock/{tId}")
    ResponseEntity<Void> deleteTransaction(@PathVariable Long id, @PathVariable Long tId) {
        customerService.apiDeleteTransaction(id, tId);

        return ResponseEntity.noContent().build();
    }
}

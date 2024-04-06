package com.example.attestation03_01.api;

import com.example.attestation03_01.dto.CustomerStockApiDto;
import com.example.attestation03_01.exception.ResourceNotFoundException;
import com.example.attestation03_01.model.Customer;
import com.example.attestation03_01.model.Stock;
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
public class CustomerApi {
    @Autowired
    CustomerService customerService;

    @Operation(summary = "Получение списка пользователей")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Список получен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = Customer.class))
                                    )
                            }
                    )
            }
    )
    @GetMapping()
    ResponseEntity<Iterable<Customer>> getCustomers() {
        return ResponseEntity.ok(customerService.apiFindAll());
    }

    @Operation(summary = "Получение пользователя")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Пользователь получен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь не найден"
                    )
            }
    )
    @GetMapping("{id}")
    ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findCustomerById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Operation(summary = "Создание пользователя")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Пользователь создан",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    )
            }
    )
    @PostMapping("")
    ResponseEntity<Customer> createCustomer(@RequestBody Customer payload) {
        return ResponseEntity.ok(customerService.apiSaveCustomer(payload));
    }

    @Operation(summary = "Обновление пользователя")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Пользователь обновлен",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь не найден"
                    )
            }
    )
    @PutMapping("")
    ResponseEntity<Void> updateCustomer(@RequestBody Customer payload) {
        customerService.apiUpdateCustomer(payload);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Удаление пользователя")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode="200",
                            description="Пользователь удален"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь не найден"
                    )
            }
    )
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.apiDeleteCustomer(id);

        return ResponseEntity.noContent().build();
    }
}

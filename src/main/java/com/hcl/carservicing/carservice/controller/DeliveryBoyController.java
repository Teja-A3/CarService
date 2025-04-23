package com.hcl.carservicing.carservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.carservicing.carservice.model.DeliveryBoy;
import com.hcl.carservicing.carservice.service.DeliveryBoyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/delivery-boys")
public class DeliveryBoyController {
    private final DeliveryBoyService deliveryBoyService;

    public DeliveryBoyController(DeliveryBoyService deliveryBoyService) {
        this.deliveryBoyService = deliveryBoyService;
    }

    // Add a new delivery boy
    @PostMapping("/add")
    public ResponseEntity<String> create(@Valid @RequestBody DeliveryBoy deliveryBoy) {
        deliveryBoyService.createDeliveryBoy(deliveryBoy);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Delivery boy created successfully");
    }

    // Update an existing delivery boy by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @Valid @RequestBody DeliveryBoy deliveryBoy) {
        deliveryBoyService.updateDeliveryBoy(id, deliveryBoy);
        return ResponseEntity.ok("Delivery boy updated successfully");
    }

    @GetMapping("/center/{centerId}")
    public ResponseEntity<List<DeliveryBoy>> byCenter(@PathVariable Long centerId) {
        List<DeliveryBoy> list = deliveryBoyService.getDeliveryBoysByCenter(centerId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/available")
    public ResponseEntity<List<DeliveryBoy>> available() {
        List<DeliveryBoy> list = deliveryBoyService.getAvailableDeliveryBoys();
        return ResponseEntity.ok(list);
    }
}
    

package com.hcl.carservicing.carservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.carservicing.carservice.model.ServiceCenterServiceType;
import com.hcl.carservicing.carservice.service.ServiceCenterServiceTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/service-center-services")
public class ServiceCenterServiceTypeController {
    private final ServiceCenterServiceTypeService service;

    public ServiceCenterServiceTypeController(ServiceCenterServiceTypeService service) {
        this.service = service;
    }

    // Add service type to service center
    @PostMapping("/add")
    public ResponseEntity<String> add(@Valid @RequestBody ServiceCenterServiceType scst) {
        service.addServiceTypeToCenter(scst);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Service type assigned to service center successfully");
    }

    // Update existing service-center service relation
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @Valid @RequestBody ServiceCenterServiceType scst) {
        service.updateServiceCenterServiceType(id, scst);
        return ResponseEntity.ok("Service center service mapping updated successfully");
    }

    // Get all services offered by a specific center
    @GetMapping("/byCenter/{centerId}")
    public ResponseEntity<Object> byCenter(@PathVariable Long centerId) {
        return ResponseEntity.ok(service.getByServiceCenter(centerId));
    }

    // Get all centers offering a specific service type
    @GetMapping("/byServiceType/{serviceTypeId}")
    public ResponseEntity<Object> byService(@PathVariable Long serviceTypeId) {
        return ResponseEntity.ok(service.getByServiceType(serviceTypeId));
    }
}
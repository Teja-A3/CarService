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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.carservicing.carservice.model.ServiceCenter;
import com.hcl.carservicing.carservice.service.ServiceCenterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/service-centers")
public class ServiceCenterController {

    private final ServiceCenterService serviceCenterService;

    
    public ServiceCenterController(ServiceCenterService serviceCenterService) {
        this.serviceCenterService = serviceCenterService;
    }

    // Add a new service center
    @PostMapping("/addServiceCenter")
    public ResponseEntity<String> addServiceCenter(@Valid @RequestBody ServiceCenter serviceCenter) {
        serviceCenterService.createServiceCenter(serviceCenter);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Service center created successfully");
    }

    // Update service center details
    @PutMapping("/updateServiceCenter/{id}")
    public ResponseEntity<String> updateServiceCenter(@PathVariable Long id,@Valid @RequestBody ServiceCenter serviceCenter) {
        serviceCenterService.updateServiceCenter(id, serviceCenter);
        return ResponseEntity.ok("Service center updated successfully");
    }

    // Get all service centers
    @GetMapping("/getAllServiceCenters")
    public ResponseEntity<List<ServiceCenter>> getAllServiceCenters() {
        List<ServiceCenter> centers = serviceCenterService.getAllServiceCenters();
        return ResponseEntity.ok(centers);
    }

    // Get service centers by availability
    @GetMapping("/getAvailableServiceCenters")
    public ResponseEntity<List<ServiceCenter>> getAvailableServiceCenters( @RequestParam(defaultValue = "true")Boolean available) {
        List<ServiceCenter> availableCenters = serviceCenterService.getAvailableServiceCenters(available);
        return ResponseEntity.ok(availableCenters);
    }

    // Get service center by ID
    @GetMapping("/getServiceCenterById/{id}")
    public ResponseEntity<ServiceCenter> getServiceCenterById(@PathVariable Long id) {
        ServiceCenter center = serviceCenterService.getServiceCenterById(id);
        return ResponseEntity.ok(center);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<String> updateStatusOfServiceCenter(@PathVariable Long id, @RequestParam Boolean status) {
        serviceCenterService.updateStatusOfServiceCenter(id, status);
        return ResponseEntity.ok("Service center status updated successfully");
    }
}



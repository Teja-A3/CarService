package com.hcl.carservicing.carservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hcl.carservicing.carservice.model.ServiceCenter;

public interface ServiceCenterService {
    ServiceCenter createServiceCenter(ServiceCenter serviceCenter);
    ServiceCenter updateServiceCenter(Long id, ServiceCenter serviceCenter);
    List<ServiceCenter> getAllServiceCenters();
    List<ServiceCenter> getAvailableServiceCenters(Boolean available);
    ServiceCenter getServiceCenterById(Long id);
	ResponseEntity<ServiceCenter> updateStatusOfServiceCenter(Long id, Boolean status);
}


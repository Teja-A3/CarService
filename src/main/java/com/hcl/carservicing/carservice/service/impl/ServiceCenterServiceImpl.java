package com.hcl.carservicing.carservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.carservicing.carservice.model.ServiceCenter;
import com.hcl.carservicing.carservice.repository.ServiceCenterRepository;
import com.hcl.carservicing.carservice.service.ServiceCenterService;

@Service
public class ServiceCenterServiceImpl implements ServiceCenterService {

    private final ServiceCenterRepository serviceCenterRepository;

    public ServiceCenterServiceImpl(ServiceCenterRepository serviceCenterRepository) {
        this.serviceCenterRepository = serviceCenterRepository;
    }

    @Override
    public ServiceCenter createServiceCenter(ServiceCenter serviceCenter) {
        return serviceCenterRepository.save(serviceCenter);
    }

    @Override
    public ServiceCenter updateServiceCenter(Long id, ServiceCenter serviceCenter) {
        Optional<ServiceCenter> existingServiceCenter = serviceCenterRepository.findById(id);
        if (existingServiceCenter.isPresent()) {
            ServiceCenter updatedServiceCenter = existingServiceCenter.get();
            updatedServiceCenter.setName(serviceCenter.getName());
            updatedServiceCenter.setAddress(serviceCenter.getAddress());
            updatedServiceCenter.setRating(serviceCenter.getRating());
            updatedServiceCenter.setAvailable(serviceCenter.getAvailable());
            return serviceCenterRepository.save(updatedServiceCenter);
        }
        return null; // or throw exception as needed
    }

    @Override
    public List<ServiceCenter> getAllServiceCenters() {
        return serviceCenterRepository.findAll();
    }

    @Override
    public List<ServiceCenter> getAvailableServiceCenters(Boolean available) {
        return serviceCenterRepository.findByAvailable(available);
    }

    @Override
    public ServiceCenter getServiceCenterById(Long id) {
        return serviceCenterRepository.findById(id).orElse(null); // or throw exception
    }

	@Override
	public ResponseEntity<ServiceCenter> updateStatusOfServiceCenter(Long id, Boolean status) {
        Optional<ServiceCenter> existingServiceCenter = serviceCenterRepository.findById(id);
        if (existingServiceCenter.isPresent()) {
            ServiceCenter updatedServiceCenter = existingServiceCenter.get();
            updatedServiceCenter.setAvailable(status);
             serviceCenterRepository.save(updatedServiceCenter);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedServiceCenter);
        }
		return null;	
	}
}


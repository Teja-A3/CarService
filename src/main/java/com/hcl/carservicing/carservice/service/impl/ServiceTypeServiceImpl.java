package com.hcl.carservicing.carservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.carservicing.carservice.model.ServiceType;
import com.hcl.carservicing.carservice.repository.ServiceTypeRepository;
import com.hcl.carservicing.carservice.service.ServiceTypeService;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {
    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @Override
    @Transactional
    public ServiceType createServiceType(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    @Override
    @Transactional
    public ServiceType updateServiceType(Long id, ServiceType serviceType) {
        ServiceType existing = serviceTypeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ServiceType not found: " + id));
        existing.setServiceName(serviceType.getServiceName());
        existing.setDescription(serviceType.getDescription());
        return serviceTypeRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceType getServiceTypeById(Long id) {
        return serviceTypeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ServiceType not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeRepository.findAll();
    }
}



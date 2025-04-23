package com.hcl.carservicing.carservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.carservicing.carservice.model.ServiceCenterServiceType;
import com.hcl.carservicing.carservice.repository.ServiceCenterServiceTypeRepository;
import com.hcl.carservicing.carservice.service.ServiceCenterServiceTypeService;

@Service
public class ServiceCenterServiceTypeServiceImpl implements ServiceCenterServiceTypeService {
    private final ServiceCenterServiceTypeRepository repository;

    public ServiceCenterServiceTypeServiceImpl(ServiceCenterServiceTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ServiceCenterServiceType addServiceTypeToCenter(ServiceCenterServiceType scst) {
        return repository.save(scst);
    }

    @Override
    @Transactional
    public ServiceCenterServiceType updateServiceCenterServiceType(Long id, ServiceCenterServiceType scst) {
        ServiceCenterServiceType existing = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ServiceCenterServiceType not found: " + id));
        existing.setServiceCenter(scst.getServiceCenter());
        existing.setServiceType(scst.getServiceType());
        existing.setCost(scst.getCost());
        return repository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceCenterServiceType> getByServiceCenter(Long serviceCenterId) {
        return repository.findByServiceCenterId(serviceCenterId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceCenterServiceType> getByServiceType(Long serviceTypeId) {
        return repository.findByServiceTypeId(serviceTypeId);
    }
}

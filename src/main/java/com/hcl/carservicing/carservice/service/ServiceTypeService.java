package com.hcl.carservicing.carservice.service;

import java.util.List;

import com.hcl.carservicing.carservice.model.ServiceType;

public interface ServiceTypeService {

    ServiceType createServiceType(ServiceType serviceType);
    ServiceType updateServiceType(Long id, ServiceType serviceType);
    ServiceType getServiceTypeById(Long id);
    List<ServiceType> getAllServiceTypes();
}

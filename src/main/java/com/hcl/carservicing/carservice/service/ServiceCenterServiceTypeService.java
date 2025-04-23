package com.hcl.carservicing.carservice.service;

import java.util.List;

import com.hcl.carservicing.carservice.model.ServiceCenterServiceType;

public interface ServiceCenterServiceTypeService {

    ServiceCenterServiceType addServiceTypeToCenter(ServiceCenterServiceType scst);
    ServiceCenterServiceType updateServiceCenterServiceType(Long id, ServiceCenterServiceType scst);
    List<ServiceCenterServiceType> getByServiceCenter(Long serviceCenterId);
    List<ServiceCenterServiceType> getByServiceType(Long serviceTypeId);

}

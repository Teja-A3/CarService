package com.hcl.carservicing.carservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.carservicing.carservice.model.DeliveryBoy;
import com.hcl.carservicing.carservice.repository.DeliveryBoyRepository;
import com.hcl.carservicing.carservice.service.DeliveryBoyService;

@Service
public class DeliveryBoyServiceImpl implements DeliveryBoyService {
    private final DeliveryBoyRepository deliveryBoyRepository;

   
    public DeliveryBoyServiceImpl(DeliveryBoyRepository deliveryBoyRepository) {
        this.deliveryBoyRepository = deliveryBoyRepository;
    }

    @Override
    @Transactional
    public DeliveryBoy createDeliveryBoy(DeliveryBoy deliveryBoy) {
    	// Check for existing contact number
        Optional<DeliveryBoy> existingContactNumber = deliveryBoyRepository.findByContactNumber(deliveryBoy.getContactNumber());
        if (existingContactNumber.isPresent()) {
            throw new IllegalArgumentException("Contact Number already exists: " + deliveryBoy.getContactNumber());
        }
        return deliveryBoyRepository.save(deliveryBoy);
    }

    @Override
    @Transactional
    public DeliveryBoy updateDeliveryBoy(Long id, DeliveryBoy deliveryBoy) {
        DeliveryBoy existing = deliveryBoyRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("DeliveryBoy not found: " + id));
        existing.setName(deliveryBoy.getName());
        existing.setContactNumber(deliveryBoy.getContactNumber());
        existing.setServiceCenter(deliveryBoy.getServiceCenter());
//        existing.setIsAvailable(deliveryBoy.getIsAvailable());
        return deliveryBoyRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeliveryBoy> getDeliveryBoysByCenter(Long serviceCenterId) {
        return deliveryBoyRepository.findByServiceCenterId(serviceCenterId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeliveryBoy> getAvailableDeliveryBoys() {
        return deliveryBoyRepository.findByServicingRequestIsNull();
    }
}



package com.hcl.carservicing.carservice.service.impl;

import java.util.List;
import java.util.Optional;

import com.hcl.carservicing.carservice.model.DeliveryBoy;
import com.hcl.carservicing.carservice.repository.DeliveryBoyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.carservicing.carservice.enums.RequestStatus;
import com.hcl.carservicing.carservice.model.ServicingRequest;
import com.hcl.carservicing.carservice.repository.ServicingRequestRepository;
import com.hcl.carservicing.carservice.service.ServicingRequestService;

@Service
public class ServicingRequestServiceImpl implements ServicingRequestService {
    private final ServicingRequestRepository repository;
    private final DeliveryBoyRepository deliveryBoyRepository;

    public ServicingRequestServiceImpl(ServicingRequestRepository repository,DeliveryBoyRepository deliveryBoyRepository) {
        this.repository = repository;
        this.deliveryBoyRepository = deliveryBoyRepository;
    }

    @Override
    @Transactional
    public ServicingRequest createRequest(ServicingRequest request) {
        // set initial status
        request.setStatus(RequestStatus.PENDING);
        return repository.save(request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicingRequest> getRequestsByUser(String username) {
        return repository.findByUserUsername(username);
    }

    @Override
    @Transactional
    public ServicingRequest updateRequestStatus(Long requestId, String status, Long deliveryBoyId) {
        ServicingRequest existing = repository.findById(requestId)
            .orElseThrow(() -> new IllegalArgumentException("Request not found: " + requestId));
        existing.setStatus(RequestStatus.valueOf(status));

        Optional<DeliveryBoy> deliveryBoy = deliveryBoyRepository.findById(deliveryBoyId);
        deliveryBoy.ifPresent(existing::setDeliveryBoy);

        return repository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicingRequest> getAllRequests() {
        return repository.findAll();
    }
}


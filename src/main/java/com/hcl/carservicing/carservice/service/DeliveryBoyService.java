package com.hcl.carservicing.carservice.service;

import java.util.List;

import com.hcl.carservicing.carservice.model.DeliveryBoy;

public interface DeliveryBoyService {

	DeliveryBoy createDeliveryBoy(DeliveryBoy deliveryBoy);

	DeliveryBoy updateDeliveryBoy(Long id, DeliveryBoy deliveryBoy);

	List<DeliveryBoy> getDeliveryBoysByCenter(Long centerId);

	List<DeliveryBoy> getAvailableDeliveryBoys();

}

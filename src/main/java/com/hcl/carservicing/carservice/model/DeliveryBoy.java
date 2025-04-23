package com.hcl.carservicing.carservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "delivery_boy")
public class DeliveryBoy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 4, max = 100, message = "Name must be between 4 and 100 characters")
    private String name;
    
    @NotBlank(message = "Contact Number is mandatory")
    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact Number must be only 10 digits")
    private String contactNumber;

	@ManyToOne
	private ServiceCenter serviceCenter;

//	@OneToOne(mappedBy = "deliveryBoy")
	private ServicingRequest servicingRequest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public ServicingRequest getServicingRequest() {
		return servicingRequest;
	}

	public void setServicingRequest(ServicingRequest servicingRequest) {
		this.servicingRequest = servicingRequest;
	}

	public ServiceCenter getServiceCenter() {
		return serviceCenter;
	}

	public void setServiceCenter(ServiceCenter serviceCenter) {
		this.serviceCenter = serviceCenter;
	}

}


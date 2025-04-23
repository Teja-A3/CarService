package com.hcl.carservicing.carservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "service_center")
public class ServiceCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 4, max = 30, message = "Name must be between 4 and 30 characters")
    private String name;

    @NotBlank(message = "Address is mandatory")
    @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
    private String address;

    @NotNull(message = "Rating is mandatory")
    @Pattern(regexp = "^(0(?:\\.0{1})?|[1-4](\\.\\d{1})?|5(\\.0{1})?)$", message = "Rating must be between 0.0 and 5.0 with at most one decimal place")
    private Double rating;

    // TODO: validation
    @OneToMany(mappedBy = "serviceCenter", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<ServicingRequest> servicingRequests;

    // TODO: validation
    @OneToMany(mappedBy = "serviceCenter", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<ServiceCenterServiceType> serviceCenterServiceTypes;

    // TODO: cascadeType, fetchType, validation
    @OneToMany(mappedBy = "serviceCenter", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<DeliveryBoy> deliveryBoys;

    private Boolean available = true; // Default value set here

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<ServicingRequest> getServicingRequests() {
        return servicingRequests;
    }

    public void setServicingRequests(List<ServicingRequest> servicingRequests) {
        this.servicingRequests = servicingRequests;
    }

    public List<ServiceCenterServiceType> getServiceCenterServiceTypes() {
        return serviceCenterServiceTypes;
    }

    public void setServiceCenterServiceTypes(List<ServiceCenterServiceType> serviceCenterServiceTypes) {
        this.serviceCenterServiceTypes = serviceCenterServiceTypes;
    }

    public List<DeliveryBoy> getDeliveryBoys() {
        return deliveryBoys;
    }

    public void setDeliveryBoys(List<DeliveryBoy> deliveryBoys) {
        this.deliveryBoys = deliveryBoys;
    }
}

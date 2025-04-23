package com.hcl.carservicing.carservice.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "service_center_service_type")
public class ServiceCenterServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Cost is mandatory")
    @Min(value = 0, message = "Cost must be greater than or equal to 0")
    private Double cost;

    // TODO: join table, validation
    @ManyToOne
    private ServiceCenter serviceCenter;

    // TODO: join table, validation
    @ManyToOne
    private ServiceType serviceType;

    // TODO: validation
    @OneToMany(mappedBy = "service", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<ServicingRequest> servicingRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public ServiceCenter getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(ServiceCenter serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}

@startuml

class AppUser {
  id: Long
  firstName: String
  lastName: String
  age: Integer
  gender: String
  contactNumber: String
  username: String
  password: String
  role: UserRole
  createdAt: LocalDateTime
  servicingRequests: List<ServicingRequest>
}

class ServiceCenter {
  id: Long
  name: String
  address: String
  rating: Double
  servicingRequests: List<ServicingRequest>
  serviceCenterServiceTypes: List<ServiceCenterServiceType>
  deliveryBoys: List<DeliveryBoy>
  available: Boolean
}

class ServiceType {
  id: Long
  serviceName: String
  description: String
}

class DeliveryBoy {
  id: Long
  name: String
  contactNumber: String
  serviceCenter: ServiceCenter
  servicingRequest: ServicingRequest
}

class ServiceRequest {
  id: Long
  createdAt: LocalDateTime
  startDate: LocalDate
  endDate: LocalDate
  status: RequestStatus
  user: AppUser
  deliveryBoy: DeliveryBoy
  service: ServiceCenterServiceType
}

class ServiceCenterServiceType {
  id: Long
  serviceCenter: ServiceCenter
  serviceType: ServiceType
  cost: Double
}

enum RequestStatus {
  PENDING
  ACCEPTED
  REJECTED
}

enum UserRole {
  ADMIN
  USER
  DELIVERY_BOY
}

ServiceCenterServiceType "0..*" -- "1" ServiceType : has

ServiceRequest "0..*" --- "1" AppUser : has
ServiceRequest "0..*" --- "0..1" DeliveryBoy : has
ServiceRequest "0..*" --- "1" ServiceCenterServiceType : has
ServiceRequest "0..*" --- "1" ServiceCenter : has

ServiceCenterServiceType "0..*" -- "1" ServiceCenter : has
DeliveryBoy "0..*" -- "1" ServiceCenter : has

@enduml

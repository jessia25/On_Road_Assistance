package com.pack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "CUSTOMER_REQUEST", schema = "ON_ROAD_ASSISTANCE")
public class CustomerRequest {

	@Id
	@Column(name = "request_id")
	private Integer requestId;

	@Column(name = "customer_id")
	@NotNull
	private Integer customerId;

	@Column(name = "customer_name")
	@NotEmpty
	@Size(min = 2, max = 50)
	private String customerName;

	@Column(name = "contact_number")
	@NotEmpty
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String contactNumber;

	@Column(name = "email")
	@Email
	@NotEmpty
	private String email;

	@Column(name = "location")
	@NotEmpty
	@Size(min = 2, max = 50)
	private String location;

	@Column(name = "latitude")
	@NotNull
	private Double latitude;

	@Column(name = "longitude")
	@NotNull
	private Double longitude;

	@Column(name = "status")

	private String status;

	@Column(name = "mechanic_id")
	private Integer mechanicId;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getMechanicId() {
		return mechanicId;
	}

	public void setMechanicId(Integer mechanicId) {
		this.mechanicId = mechanicId;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
}

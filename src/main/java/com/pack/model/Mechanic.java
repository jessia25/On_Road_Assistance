package com.pack.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "MECHANIC", schema = "ON_ROAD_ASSISTANCE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Mechanic {

	@Column(name = "mechanic_name")
	@NotEmpty
	@Size(min = 2, max = 30)
	private String mechanicName;

	@Id
	@Column(name = "mechanic_id")
	private Integer mechanicId;

	@Column(name = "password")
	@NotEmpty
	@Size(min = 2, max = 30)
	private String password;

	@Column(name = "gender")
	@NotEmpty
	private String gender;

	@Column(name = "date_of_birth")
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth;

	@Column(name = "contact_number")
	@Pattern(regexp = "(^$|[0-9]{10})")
	@NotEmpty
	private String contactNumber;

	@Column(name = "email")
	@Email
	private String email;

	@Column(name = "latitude")
	@NotNull
	private Double latitude;

	@Column(name = "longitude")
	@NotNull
	private Double longitude;

	public String getMechanicName() {
		return mechanicName;
	}

	public void setMechanicName(String mechanicName) {
		this.mechanicName = mechanicName;
	}

	public Integer getMechanicId() {
		return mechanicId;
	}

	public void setMechanicId(Integer mechanicId) {
		this.mechanicId = mechanicId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
}

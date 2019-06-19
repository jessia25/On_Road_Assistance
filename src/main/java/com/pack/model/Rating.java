package com.pack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RATING", schema = "ON_ROAD_ASSISTANCE")
public class Rating {

	@Id
	@Column(name = "ratingId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer serialNo;

	@Column(name = "mechanic_id")
	private Integer mechanicId;

	@Column(name = "customer_id")
	@NotNull
	private Integer customerId;

	@Column(name = "rating")
	@NotNull
	private Integer rating;

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public Integer getMechanicId() {
		return mechanicId;
	}

	public void setMechanicId(Integer mechanicId) {
		this.mechanicId = mechanicId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}

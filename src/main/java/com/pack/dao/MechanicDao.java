package com.pack.dao;

import java.util.List;

import com.pack.model.Customer;
import com.pack.model.CustomerRequest;
import com.pack.model.Mechanic;

public interface MechanicDao {

	public void addMechanic(Mechanic mechanic);

	public List<CustomerRequest> getRequestList(Integer mechanicId);

	public int rejectRequest(Integer requestId);

	public int acceptRequest(Integer requestId);

	public Mechanic fetchMechanicById(Integer mechanicId);

	public CustomerRequest getRequestById(int requestId);

	public void updateStatus(Integer requestId);

	public Customer getCustomerById(Integer customerId);
}

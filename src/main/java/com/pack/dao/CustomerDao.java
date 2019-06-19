package com.pack.dao;

import java.util.List;

import com.pack.model.Customer;
import com.pack.model.CustomerRequest;
import com.pack.model.Mechanic;
import com.pack.model.Rating;
import com.pack.model.RatingAvg;
import com.pack.model.User;

public interface CustomerDao {

	public Customer getCustomerById(int customerId);

	public User checkUser(User user);

	public void addCustomer(Customer customer);

	public void addCustomerRequest(CustomerRequest customerRequest);

	public List<RatingAvg> listMechanics();

	public Mechanic getMechanicById(Integer mechanicId);

	public void sendRequest(CustomerRequest customerRequest);

	public List<CustomerRequest> getRequest(Integer customerId);

	public CustomerRequest getRequestById(int requestId);

	public int deleteRequest(Integer requestId);

	public CustomerRequest checkRequests(Integer customerId);

	public void rateMechanic(Rating r);

	public void updateRequest(Integer requestId);

}

package com.pack.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pack.dao.CustomerDao;
import com.pack.model.Customer;
import com.pack.model.CustomerRequest;
import com.pack.model.Mechanic;
import com.pack.model.Rating;
import com.pack.model.RatingAvg;
import com.pack.model.User;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static Logger log = Logger.getLogger(CustomerServiceImpl.class);

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Transactional
	public Customer getCustomerById(int customerId) {

		log.info("Inside Customer Service - Fetch Customer By ID");
		Customer customer = customerDao.getCustomerById(customerId);
		return customer;
	}

	@Transactional
	public User checkUser(User user) {

		log.info("Inside Service - Validating User");
		User user1 = customerDao.checkUser(user);
		return user1;
	}

	@Transactional
	public void addCustomer(Customer customer) {

		log.info("Inside Customer Service - Adding a Customer");
		customerDao.addCustomer(customer);

	}

	@Transactional
	public void addCustomerRequest(CustomerRequest customerRequest) {
		log.info("Inside Customer Service - Add Customer Request");
		customerDao.addCustomerRequest(customerRequest);

	}

	@Transactional
	public List<RatingAvg> listMechanics() {

		log.info("Inside Customer Service - Get List of Mechanics");
		List<RatingAvg> list = customerDao.listMechanics();

		return list;
	}

	@Transactional
	public Mechanic getMechanicById(Integer mechanicId) {

		log.info("Inside Customer Service - Fetch Mechanic By ID");
		Mechanic mechanic = customerDao.getMechanicById(mechanicId);
		return mechanic;
	}

	@Transactional
	public void sendRequest(CustomerRequest customerRequest) {
		log.info("Inside Customer Service - Send Request");
		customerDao.sendRequest(customerRequest);

	}

	@Transactional
	public List<CustomerRequest> getRequest(Integer customerId) {
		log.info("Inside Customer Service - Get List of Active Requests");
		List<CustomerRequest> list = customerDao.getRequest(customerId);
		return list;
	}

	@Transactional
	public CustomerRequest getRequestById(int requestId) {
		log.info("Inside Customer Service - Fetch Request By ID");
		CustomerRequest customerRequest = customerDao.getRequestById(requestId);
		return customerRequest;
	}

	@Transactional
	public int deleteRequest(Integer requestId) {
		log.info("Inside Customer Service - Deleting a Request");
		int i = customerDao.deleteRequest(requestId);
		return i;
	}

	@Transactional
	public CustomerRequest checkRequests(Integer customerId) {

		log.info("Inside Customer Service - Checking Already Available Requests");
		CustomerRequest customerRequest = customerDao.checkRequests(customerId);
		return customerRequest;
	}

	@Transactional
	public void rateMechanic(Rating r) {
		log.info("Inside Customer Service - Rating a Mechanic");
		customerDao.rateMechanic(r);

	}

	@Transactional
	public void updateRequest(Integer requestId) {

		log.info("Inside Customer Service - Updating Request After Rating");
		customerDao.updateRequest(requestId);

	}

}

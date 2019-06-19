package com.pack.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pack.dao.MechanicDao;
import com.pack.model.Customer;
import com.pack.model.CustomerRequest;
import com.pack.model.Mechanic;

@Service
public class MechanicServiceImpl implements MechanicService {

	private static Logger log = Logger.getLogger(MechanicServiceImpl.class);

	private MechanicDao mechanicDao;

	public void setMechanicDao(MechanicDao mechanicDao) {
		this.mechanicDao = mechanicDao;
	}

	@Transactional
	public void addMechanic(Mechanic mechanic) {
		log.info("Inside Mechanic Service - Add Mechanic");
		mechanicDao.addMechanic(mechanic);

	}

	@Transactional
	public List<CustomerRequest> getRequestList(Integer mechanicId) {

		log.info("Inside Mechanic Service - Get List of Requests for Mechanic");
		List<CustomerRequest> list = mechanicDao.getRequestList(mechanicId);
		return list;
	}

	@Transactional
	public int rejectRequest(Integer requestId) {
		log.info("Inside Mechanic Service - Rejecting a Request");
		int i = mechanicDao.rejectRequest(requestId);
		return i;
	}

	@Transactional
	public int acceptRequest(Integer requestId) {
		log.info("Inside Mechanic Service - Accepting a Request");
		int i = mechanicDao.acceptRequest(requestId);
		return i;
	}

	@Transactional
	public Mechanic fetchMechanicById(Integer mechanicId) {

		log.info("Inside Mechanic Service - Fetch Mechanic By ID");
		Mechanic mechanic = mechanicDao.fetchMechanicById(mechanicId);
		return mechanic;
	}

	@Transactional
	public CustomerRequest getRequestById(int requestId) {
		log.info("Inside Mechanic Service - Fetch Request By ID");
		CustomerRequest customerRequest = mechanicDao.getRequestById(requestId);
		return customerRequest;
	}

	@Transactional
	public void updateStatus(Integer requestId) {
		log.info("Inside Mechanic Service - Update Status After Completing Service");
		mechanicDao.updateStatus(requestId);

	}

	@Transactional
	public Customer getCustomerById(Integer customerId) {

		log.info("Inside Mechanic Service - Fetch Customer By ID");
		Customer customer = mechanicDao.getCustomerById(customerId);
		return customer;
	}

}

package com.pack.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.pack.model.Customer;
import com.pack.model.CustomerRequest;
import com.pack.model.Mechanic;
import com.pack.model.Rating;
import com.pack.model.User;

public class MechanicDaoImpl implements MechanicDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private static Logger log = Logger.getLogger(MechanicDaoImpl.class);

	public void addMechanic(Mechanic mechanic) {
		log.info("Inside Mechanic DAO - Add Mechanic");
		sessionFactory.getCurrentSession().save(mechanic);

		log.info("Inside Mechanic DAO - Adding Mechanic To User");
		User user = new User();
		user.setUserId(mechanic.getMechanicId());
		user.setPassword(mechanic.getPassword());
		user.setType("M");
		sessionFactory.getCurrentSession().save(user);

		log.info("Inside Mechanic DAO - Adding Mechanic To The Rating List");
		Rating rating = new Rating();
		rating.setMechanicId(mechanic.getMechanicId());
		rating.setRating(5);
		rating.setCustomerId(null);

		sessionFactory.getCurrentSession().save(rating);
	}

	public List<CustomerRequest> getRequestList(Integer mechanicId) {

		log.info("Inside Mechanic DAO - Getting List of Requests for Mechanic");
		Query q = sessionFactory.getCurrentSession().createQuery("from CustomerRequest c where c.mechanicId=:id");
		q.setParameter("id", mechanicId);
		List list = q.list();
		return list;
	}

	public int rejectRequest(Integer requestId) {

		log.info("Inside Mechanic DAO - Rejecting a Request");
		Query q = sessionFactory.getCurrentSession()
				.createQuery("update CustomerRequest c set c.status=:status where c.requestId=:id");
		q.setParameter("status", "rejected");
		q.setParameter("id", requestId);
		int i = q.executeUpdate();
		return i;
	}

	public int acceptRequest(Integer requestId) {

		log.info("Inside Mechanic DAO - Accepting a Request");
		Query q = sessionFactory.getCurrentSession()
				.createQuery("update CustomerRequest c set c.status=:status where c.requestId=:id");
		q.setParameter("status", "accepted");
		q.setParameter("id", requestId);
		int i = q.executeUpdate();
		return i;
	}

	public Mechanic fetchMechanicById(Integer mechanicId) {

		log.info("Inside Mechanic DAO - Fetch Mechanic by ID");
		Query q = sessionFactory.getCurrentSession().createQuery("from Mechanic m where m.mechanicId=:id");
		q.setParameter("id", mechanicId);
		Mechanic mechanic = (Mechanic) q.uniqueResult();
		return mechanic;
	}

	public CustomerRequest getRequestById(int requestId) {

		log.info("Inside Mechanic DAO - Fetch Request By ID");
		Query q = sessionFactory.getCurrentSession().createQuery("from CustomerRequest c where c.requestId=:id");
		q.setParameter("id", requestId);
		CustomerRequest customerRequest = (CustomerRequest) q.uniqueResult();
		return customerRequest;
	}

	public void updateStatus(Integer requestId) {

		log.info("Inside Mechanic DAO - Update Status After Completing Service");
		Query q = sessionFactory.getCurrentSession()
				.createQuery("update CustomerRequest c set c.status=:status where requestId=:id");
		q.setParameter("status", "completed");
		q.setParameter("id", requestId);
		q.executeUpdate();
	}

	public Customer getCustomerById(Integer customerId) {

		log.info("Inside Mechanic DAO - Fetch Customer By ID");
		Query q = sessionFactory.getCurrentSession().createQuery("from Customer c where c.customerId=:id");
		q.setParameter("id", customerId);
		Customer customer = (Customer) q.uniqueResult();
		return customer;
	}
}

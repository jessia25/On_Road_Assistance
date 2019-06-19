package com.pack.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.pack.model.Customer;
import com.pack.model.CustomerRequest;
import com.pack.model.Mechanic;
import com.pack.model.Rating;
import com.pack.model.RatingAvg;
import com.pack.model.User;

public class CustomerDaoImpl implements CustomerDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private static Logger log = Logger.getLogger(CustomerDaoImpl.class);

	public User checkUser(User user) {

		log.info("Inside DAO - Validating User");

		Query q = sessionFactory.getCurrentSession()
				.createQuery("from User u where u.userId=:id and u.password=:password");
		q.setParameter("id", user.getUserId());
		q.setParameter("password", user.getPassword());
		User user1 = (User) q.uniqueResult();
		return user1;
	}

	public Customer getCustomerById(int customerId) {

		log.info("Inside Customer DAO - Fetch Customer by ID");
		Query q = sessionFactory.getCurrentSession().createQuery("from Customer c where c.id=:id");
		q.setParameter("id", customerId);
		Customer customer = (Customer) q.uniqueResult();
		return customer;
	}

	public void addCustomer(Customer customer) {
		log.info("Inside Customer DAO - Add Customer");
		sessionFactory.getCurrentSession().save(customer);

		log.info("Inside Customer DAO - Adding Customer to User Table");
		User user = new User();
		user.setUserId(customer.getCustomerId());
		user.setPassword(customer.getPassword());
		user.setType("C");

		sessionFactory.getCurrentSession().save(user);

	}

	public void addCustomerRequest(CustomerRequest customerRequest) {
		log.info("Inside Customer DAO - Add Request");
		sessionFactory.getCurrentSession().saveOrUpdate(customerRequest);

	}

	public List<RatingAvg> listMechanics() {

		log.info("Inside Customer DAO - List Mechanics");
		Query q = sessionFactory.getCurrentSession().createSQLQuery(
				"select r.mechanic_id,avg(r.rating) as rat from rating r group by r.mechanic_id order by rat desc");
		List<Object[]> rows = q.list();
		List<RatingAvg> l = new ArrayList<>();
		for (Object[] row : rows) {
			RatingAvg avg = new RatingAvg();
			avg.setMechanicId(Integer.parseInt(row[0].toString()));
			avg.setAvg(Double.parseDouble(row[1].toString()));
			l.add(avg);
		}
		return l;
	}

	public Mechanic getMechanicById(Integer mechanicId) {

		log.info("Inside Customer DAO - Fetching Mechanic by ID");
		Query q = sessionFactory.getCurrentSession().createQuery("from Mechanic m where m.mechanicId=:id");
		q.setParameter("id", mechanicId);
		Mechanic mechanic = (Mechanic) q.uniqueResult();
		return mechanic;
	}

	public void sendRequest(CustomerRequest customerRequest) {

		log.info("Inside Customer DAO - Sending Request");
		sessionFactory.getCurrentSession().saveOrUpdate(customerRequest);

	}

	public List<CustomerRequest> getRequest(Integer customerId) {
		log.info("Inside Customer DAO - Getting Active Requests");
		Query q = sessionFactory.getCurrentSession().createQuery("from CustomerRequest c where c.customerId=:id");
		q.setParameter("id", customerId);
		List list = q.list();
		return list;
	}

	public CustomerRequest getRequestById(int requestId) {

		log.info("Inside Customer DAO - Fetch Request by ID");
		Query q = sessionFactory.getCurrentSession().createQuery("from CustomerRequest c where c.requestId=:id");
		q.setParameter("id", requestId);
		CustomerRequest customerRequest = (CustomerRequest) q.uniqueResult();
		return customerRequest;
	}

	public int deleteRequest(Integer requestId) {

		log.info("Inside Customer DAO - Delete a Request");
		Query q = sessionFactory.getCurrentSession().createQuery("delete from CustomerRequest c where c.requestId=:id");
		q.setParameter("id", requestId);
		int i = q.executeUpdate();
		return i;
	}

	public CustomerRequest checkRequests(Integer customerId) {
		log.info("Inside Customer DAO - Checking Already Available Request");
		Query q = sessionFactory.getCurrentSession()
				.createQuery("from CustomerRequest c where c.status=null or c.status=:status and c.customerId=:id");
		q.setParameter("status", "wait");
		q.setParameter("id", customerId);
		CustomerRequest customerRequest = (CustomerRequest)q.uniqueResult();
		System.out.println(customerRequest);
		return customerRequest;
	}

	public void rateMechanic(Rating r) {
		log.info("Inside Customer DAO - Rating Mechanic");
		sessionFactory.getCurrentSession().save(r);

	}

	public void updateRequest(Integer requestId) {

		log.info("Inside Customer DAO - Updating Request after Rating");
		Query q = sessionFactory.getCurrentSession()
				.createQuery("update CustomerRequest c set c.status=:status where c.requestId=:id");
		q.setParameter("status", "rated");
		q.setParameter("id", requestId);
		q.executeUpdate();
	}

}

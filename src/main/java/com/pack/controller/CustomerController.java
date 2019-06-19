package com.pack.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pack.model.Customer;
import com.pack.model.CustomerRequest;
import com.pack.model.Mechanic;
import com.pack.model.Rating;
import com.pack.model.RatingAvg;
import com.pack.model.User;
import com.pack.service.CustomerService;

@Controller
public class CustomerController {

	private static Logger log = Logger.getLogger(CustomerController.class);

	private CustomerService customerService;

	@Autowired(required = true)
	@Qualifier(value = "customerService")
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping(value = "/login")
	public String checkUser(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		log.info("Inside Controller - Login");
		HttpSession session = request.getSession();

		User user = new User();
		user.setUserId(Integer.parseInt(request.getParameter("userid")));
		user.setPassword(request.getParameter("password"));

		User user1 = customerService.checkUser(user);

		if (user1 != null) {
			log.info("Passes validation");
			if (user1.getType().equals("C")) {
				log.info("Validated as Cusomter");
				session.setAttribute("customerId", user.getUserId());
				Customer customer = customerService.getCustomerById(user.getUserId());
				model.addAttribute("customer", customer);
				return "CustomerHome";
			} else if (user1.getType().equals("M")) {
				log.info("Validated as Mechanic");
				session.setAttribute("mechanicId", user.getUserId());
				return "redirect:/mechanic/login";
			}
		} else {
			log.info("Validation Failed");
			model.addAttribute("message", "Check Login Details");
			return "Login";
		}
		log.info("Returning to Login Page");
		return "Login";
	}

	@RequestMapping(value = "/registerCustomer", method = RequestMethod.GET)
	public String registerCustomer(Model model) {
		log.info("Calling Customer Registration Page");
		model.addAttribute("customer", new Customer());
		return "CustomerRegister";
	}

	@RequestMapping(value = "/customer/registerCustomer", method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute("customer") @Validated Customer customer, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response) {
		log.info("Inside Customer Controller - Registration");
		if (bindingResult.hasErrors()) {
			log.info("Validation Error");
			return "CustomerRegister";
		} else {
			log.info("Validation Passed");
			Random rand = new Random();
			int num = rand.nextInt(900000) + 1000000;
			customer.setCustomerId(num);
			log.info("Calling Customer Service - Add Customer");
			customerService.addCustomer(customer);
			HttpSession session = request.getSession();
			session.setAttribute("customerId", customer.getCustomerId());
		}
		log.info("Loading Customer Home Page");
		return "CustomerHome";
	}

	@RequestMapping(value = "/customer/request", method = RequestMethod.GET)
	public String requestMechanic(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {

		log.info("Inside Customer Controller - Request Creation");

		HttpSession session = request.getSession();
		int customerId = (int) session.getAttribute("customerId");

		log.info("Getting details for Customer Id " + customerId);

		Customer customer = customerService.getCustomerById(customerId);
		CustomerRequest customerRequest = new CustomerRequest();
		customerRequest.setCustomerId(customer.getCustomerId());
		customerRequest.setContactNumber(customer.getContactNumber());
		customerRequest.setCustomerName(customer.getCustomerName());
		customerRequest.setEmail(customer.getEmail());

		map.put("customerRequest", customerRequest);

		log.info("Loading Customer Request Page");
		return "CustomerRequest";
	}

	@RequestMapping(value = "/customer/search", method = RequestMethod.POST)
	public String searchMechanic(@ModelAttribute("customerRequest") @Validated CustomerRequest customerRequest,BindingResult bindingResult,
			Model model) {

		log.info("Inside Customer Controller - Search Mechanic");

		if (bindingResult.hasErrors()) {
			log.info("Validation Error");
			return "CustomerRequest";
		}
		else { 
			if (customerRequest.getRequestId() != null) {
			log.info("Request Already Loaded");
			customerService.addCustomerRequest(customerRequest);
			model.addAttribute("requestId", customerRequest.getRequestId());
		}

			else if (customerService.checkRequests(customerRequest.getCustomerId()) != null) {

			log.info("Customer Already Has a Request");

			CustomerRequest customerRequest1 = customerService.checkRequests(customerRequest.getCustomerId());
			customerRequest.setRequestId(customerRequest1.getRequestId());
			customerService.addCustomerRequest(customerRequest);
			model.addAttribute("requestId", customerRequest.getRequestId());
		}

		else {

			log.info("Creating New Request");

			Random rand = new Random();
			int num = rand.nextInt(900000) + 1000000;
			customerRequest.setRequestId(num);
			customerService.addCustomerRequest(customerRequest);
			model.addAttribute("requestId", num);
		}
		log.info("Creating Mechanic List");
		List<RatingAvg> list = customerService.listMechanics();
		for (RatingAvg r : list)
			System.out.println(r.getMechanicId() + " " + r.getAvg());
		model.addAttribute("ratingList", list);

		log.info("Loading List of Mechanics Page");
		return "MechanicList";
		}
	}

	@RequestMapping("/search/{requestId}/{rating.mechanicId}")
	public String listMechanic(@PathVariable("rating.mechanicId") Integer mechanicId,
			@PathVariable("requestId") Integer requestId, Map<String, Object> map) {

		log.info("Inside Customer Controller - List Mechanic");
		map.put("mechanic", customerService.getMechanicById(mechanicId));

		log.info("Getting Details for Mechanic Id " + mechanicId);
		map.put("requestId", requestId);

		log.info("Loading Details of Selected Mechanic");
		return "MechanicDetails";
	}

	@RequestMapping("/sendRequest/{requestId}/{mechanic.mechanicId}")
	public String sendRequest(@PathVariable("mechanic.mechanicId") Integer mechanicId,
			@PathVariable("requestId") Integer requestId, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {

		log.info("Inside Customer Controller - Send Request");

		CustomerRequest customerRequest = customerService.getRequestById(requestId);
		if (customerRequest != null) {
			log.info("Customer Controller - Send Request - Updating Customer Request");

			customerRequest.setMechanicId(mechanicId);
			customerRequest.setStatus("wait");
			customerService.sendRequest(customerRequest);
		}

		log.info("Loading Active Requests Page");
		return "redirect:/activeRequests";
	}

	@RequestMapping("/activeRequests")
	public String activeRequest(HttpServletRequest request, HttpServletResponse response, Model model) {

		log.info("Inside Customer Controller - Active Requests");
		HttpSession session = request.getSession();
		Integer customerId = (Integer) session.getAttribute("customerId");

		log.info("Getting Active Requests");
		model.addAttribute("customerRequestList", customerService.getRequest(customerId));

		log.info("Loading Active Requests Page");
		return "ActiveCustomerRequests";
	}

	@RequestMapping("/customer/relistMechanic/{customerRequest.requestId}")
	public String relistMechanic(@PathVariable("customerRequest.requestId") Integer requestId,
			@ModelAttribute("customerRequest") CustomerRequest customerRequest, Model model) {

		log.info("Inside Customer Controller - Relisting Mechanics");

		List<RatingAvg> list = customerService.listMechanics();
		for (RatingAvg r : list)
			System.out.println(r.getMechanicId() + " " + r.getAvg());
		model.addAttribute("ratingList", list);
		model.addAttribute("requestId", requestId);

		log.info("Loading List of Mechanics Page");
		return "MechanicList";
	}

	@RequestMapping("/customer/deleteRequest/{request.requestId}")
	public String rejectRequest(@PathVariable("request.requestId") Integer requestId) {

		log.info("Inside Customer Controller - Delete Request");

		int i = customerService.deleteRequest(requestId);

		log.info("Loading Active Requests Page");
		return "redirect:/activeRequests";
	}

	@RequestMapping("/customer/home")
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) {

		log.info("Inside Customer Controller - Loading Home");
		HttpSession session = request.getSession();
		Integer customerId = (Integer) session.getAttribute("customerId");

		log.info("Adding Customer Details to display in Home Page");
		model.addAttribute("customer", customerService.getCustomerById(customerId));

		log.info("Loading Customer Home Page");
		return "CustomerHome";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		log.info("Calling Controller - Logout");
		HttpSession session = request.getSession();
		session.invalidate();

		log.info("Loading Login Page - Session Invalidated");
		return "Login";
	}

	@RequestMapping("/customer/acceptedRequest/{customerRequest.requestId}")
	public String acceptRequest(@PathVariable("customerRequest.requestId") Integer requestId, Model model) {

		log.info("Inside Customer Controller - Navigation");
		CustomerRequest customerRequest = customerService.getRequestById(requestId);
		Mechanic mechanic = customerService.getMechanicById(customerRequest.getMechanicId());

		Double lat1 = customerRequest.getLatitude();
		Double long1 = customerRequest.getLongitude();

		Double lat2 = mechanic.getLatitude();
		Double long2 = mechanic.getLongitude();

		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		long1 = Math.toRadians(long1);
		long2 = Math.toRadians(long2);

		double dlon = long2 - long1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		double r = 6371;
		double dist = c * r;

		System.out.println(dist + "kms");

		int time = (int) dist * 2;

		int hours = time / 60;
		int minutes = time % 60;

		String t1 = String.valueOf(hours);
		String t2 = String.valueOf(minutes);
		String t = t1 + "h" + t2 + "m";

		model.addAttribute("time", t);
		model.addAttribute("mechanic", mechanic);
		model.addAttribute("requestId", requestId);

		log.info("Loading Customer Navigation Page");
		return "CustomerNavigation";
	}

	@RequestMapping("/customer/rate/{customerRequest.requestId}")
	public String rate(@PathVariable("customerRequest.requestId") Integer requestId, Model model) {

		log.info("Inside Customer Controller - Calling Rating Page");
		CustomerRequest request = customerService.getRequestById(requestId);
		Mechanic mechanic = customerService.getMechanicById(request.getMechanicId());
		model.addAttribute("mechanic", mechanic);
		model.addAttribute("requestId", requestId);

		log.info("Calling Customer Navigation Page with Rating");
		return "CustomerNavigation";
	}

	@RequestMapping("/customer/rating/{requestId}/{mechanicId}/{rat}")
	public String rateMechanic(@PathVariable("mechanicId") Integer mechanicId,
			@PathVariable("requestId") Integer requestId, @PathVariable("rat") Integer rating,
			HttpServletRequest request, HttpServletResponse response) {

		log.info("Inside Customer Controller - Rate Mechanic");
		HttpSession session = request.getSession();
		CustomerRequest customerRequest = customerService.getRequestById(requestId);
		if (customerRequest.getStatus().equals("completed")) {

			log.info("Checking if the Service is Completed");
			Integer customerId = (Integer) session.getAttribute("customerId");
			Rating r = new Rating();
			r.setMechanicId(mechanicId);
			r.setRating(rating);
			r.setCustomerId(customerId);
			customerService.rateMechanic(r);
			customerService.updateRequest(requestId);

			log.info("Returning Home After Successfully Rating");
			return "redirect:/customer/home";
		} else {

			log.info("Returning Active Requests After Failed Rating");
			return "redirect:/activeRequests";
		}
	}
	
	@RequestMapping("/loginPage")
	public String loadLogin()
	{
		return "Login";
	}
}

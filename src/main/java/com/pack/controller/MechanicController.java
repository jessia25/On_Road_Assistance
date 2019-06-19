package com.pack.controller;

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
import com.pack.service.MechanicService;

@Controller
public class MechanicController {

	private static Logger log = Logger.getLogger(CustomerController.class);

	private MechanicService mechanicService;

	@Autowired(required = true)
	@Qualifier(value = "mechanicService")
	public void setMechanicService(MechanicService mechanicService) {
		this.mechanicService = mechanicService;
	}

	@RequestMapping(value = "/mechanic/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		log.info("Inside Mechanic Controller - Login");
		HttpSession session = request.getSession();
		Integer mechanicId = (Integer) session.getAttribute("mechanicId");
		Mechanic mechanic = mechanicService.fetchMechanicById(mechanicId);
		model.addAttribute("mechanic", mechanic);
		
		log.info("Loading Mechanic Home Page");
		return "MechanicHome";
	}

	@RequestMapping(value = "/registerMechanic", method = RequestMethod.GET)
	public String registerMechanic(Model model) {
		
		log.info("Calling Mechanic Registration Page");
		model.addAttribute("mechanic", new Mechanic());
		
		return "MechanicRegister";
	}

	@RequestMapping(value = "/mechanic/registerMechanic", method = RequestMethod.POST)
	public String addMechanic(@ModelAttribute("mechanic") @Validated Mechanic mechanic, BindingResult bindingResult,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.info("Inside Mechanic Controller - Register Mechanic");
		HttpSession session = request.getSession();
		if (bindingResult.hasErrors()) {
			log.info("Validation Error");
			return "MechanicRegister";
		} else {
			
			log.info("Validation Passed");
			Random rand = new Random();
			int num = rand.nextInt(900000) + 1000000;
			mechanic.setMechanicId(num);
			log.info("Inside Mechanic Controller - Adding Mechanic");
			mechanicService.addMechanic(mechanic);
			session.setAttribute("mechanicId", num);
		}
		
		log.info("Loading Mechanic Home Page");
		return "MechanicHome";
	}

	@RequestMapping("/mechanic/request")
	public String activeRequest(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		log.info("Inside Mechanic Controller - Mechanic Requests");
		HttpSession session = request.getSession();
		Integer mechanicId = (Integer) session.getAttribute("mechanicId");
		model.addAttribute("activeRequestList", mechanicService.getRequestList(mechanicId));
		
		log.info("Loading List of Requests for Mechanic");
		return "MechanicRequests";
	}

	@RequestMapping("/mechanicAccept/{request.requestId}")
	public String acceptRequest(@PathVariable("request.requestId") Integer requestId, Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		
		log.info("Inside Mechanic Controller - Accepting a Request");
		HttpSession session = request.getSession();
		Integer mechanicId = (Integer) session.getAttribute("mechanicId");
		Mechanic mechanic = mechanicService.fetchMechanicById(mechanicId);

		CustomerRequest customerRequest = mechanicService.getRequestById(requestId);
		int i = mechanicService.acceptRequest(requestId);

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

		int time = (int) dist * 2;

		int hours = time / 60;
		int minutes = time % 60;

		String t1 = String.valueOf(hours);
		String t2 = String.valueOf(minutes);
		String t = t1 + "h" + t2 + "m";

		Integer customerId = customerRequest.getCustomerId();
		Customer customer = mechanicService.getCustomerById(customerId);
		model.addAttribute("customer", customer);
		model.addAttribute("time", t);
		model.addAttribute("requestId", requestId);
		
		log.info("Loading Mechanic Navigation Page");
		return "MechanicNavigation";
	}

	@RequestMapping("/mechanicReject/{request.requestId}")
	public String rejectRequest(@PathVariable("request.requestId") Integer requestId) {

		log.info("Inside Mechanic Controller - Rejecting a Request");
		int i = mechanicService.rejectRequest(requestId);
		
		log.info("Redirecting to Requests Page ");
		return "redirect:/mechanic/request";
	}

	@RequestMapping("/mechanic/home")
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		log.info("Inside Mechanic Controller - Loading Home");
		HttpSession session = request.getSession();
		Integer mechanicId = (Integer) session.getAttribute("mechanicId");
		
		log.info("Adding Mechanic Details to display in Home Page");
		model.addAttribute("mechanic", mechanicService.fetchMechanicById(mechanicId));
		
		log.info("Loading Mechanic Home Page");
		return "MechanicHome";
	}

	@RequestMapping(value = "/mechanic/updateStatus/{requestId}", method = RequestMethod.POST)
	public String updateStatus(@PathVariable("requestId") Integer requestId) {
		
		log.info("Inside Mechanic Controller - Completing Service");
		mechanicService.updateStatus(requestId);
		
		log.info("Redirecting to Home Page");
		return "redirect:/mechanic/home";
	}

}

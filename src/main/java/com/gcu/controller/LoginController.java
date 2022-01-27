package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.business.OrdersBusinessServiceInterface;

import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private OrdersBusinessServiceInterface service;
	
	
	
	@GetMapping("/")
	
	public String display(Model model) {
		
		
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		
		return "login";
		
		
	}
	
	
//	
	@PostMapping("/doLogin")
public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("title", "Login Form");
			return "login";
		}
		List<OrderModel> orders = service.findAll();
		System.out.println(service.findbyId(4));
		System.out.println(String.format("username %s and %s Password", loginModel.getUsername(), loginModel.getPassword()));
	
		model.addAttribute("title", "My Orders");
		model.addAttribute("orders", orders);
		
	
		return "orders";
	}
	@GetMapping("/delete")
	public String displayDeleteForm(@RequestParam("id") int id, Model model) 
	{	
		
			OrderModel order = service.findbyId(id);
			
			System.out.println(id);
			model.addAttribute("title", "Are you sure you want to delete?");
			model.addAttribute("order", order);
			
			System.out.println(id);
		
		
		return "DeleteConfirmation";
	}
	
	
	@PostMapping("/processdelete")
	public String deleteAlbum(OrderModel order, Model model)
	{	
		System.out.println("Index: " + order);
		
			service.delete(order.getId());	
			System.out.println("Deleted Album is " + order);
			
			model.addAttribute("title", "Home");
			model.addAttribute("order", order);
	
		return "index";
	}
	
	@GetMapping("/editForm")
	public String displayEditForm(@RequestParam("id") long id, Model model)
	{
		
			OrderModel order = service.findbyId(id);
			System.out.println("Album info Incomming: " + order.getId());
			model.addAttribute("title", "Edit order");			
			model.addAttribute("order", order);		
		
		
		
		return "edit";
	}
	// may be wrong.
	@PostMapping("/edit")
	public String edit(@RequestParam("id") long id, OrderModel orderModel, Model model)
	{
	
		orderModel.setId(id);
		
		
		service.update(orderModel);
		return "index";
	
			
}
	
	@GetMapping("/OrderForm")
	public String OrderForm(Model model) {
	model.addAttribute("title", "New OrderForm");
	model.addAttribute("order", new OrderModel());
	
	return "create";
	
	
}
	

	@PostMapping("/doCreate")
public String CreateOrder(@Valid OrderModel orderModel, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("title", "Register Form");
			return "register";
		}
		
		service.create(orderModel);
		System.out.println(orderModel);
		model.addAttribute("title", " Home ");

		
		return "index";

	}
	
	
}

package com.phones.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.phones.Models.Orders;
import com.phones.Models.Products;
import com.phones.Models.orderKeys;
import com.phones.Models.orderidandvalue;
import com.phones.Services.orderService;

@RestController
public class ordersController {

	@Autowired
	private orderService orderserv;
	
	@PostMapping("/orders")
	public orderidandvalue soldproducts(@RequestBody List<orderKeys> product) {
		return orderserv.soldproducts(product);
	}
}

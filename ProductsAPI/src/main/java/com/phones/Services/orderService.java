package com.phones.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phones.Models.Orders;
import com.phones.Models.Products;
import com.phones.Models.orderKeys;
import com.phones.Models.orderidandvalue;
import com.phones.Repositories.ordersRepo;
import com.phones.Repositories.repoproducts;
import com.phones.Repositories.totalOrderrepo;

@Service
public class orderService {

	@Autowired
	private repoproducts repprods;
	@Autowired
	private ordersRepo ordersrepo;
	@Autowired
	private totalOrderrepo totalorderrepo;
	
	public orderidandvalue soldproducts(List<orderKeys> product) {
		List<Products> prods=repprods.findAll();
		List<Orders> orderlist=new ArrayList<Orders>();
		long orderidcounter=totalorderrepo.count()+1;
		
		orderidandvalue idvalue=new orderidandvalue(orderidcounter, 0, 0.0);
		totalorderrepo.save(idvalue);
		
		Orders order;
		
		for(orderKeys k:product) {
			for(Products p:prods) {
				if(k.getProductname().equals(p.getProductname())) {
					order=new Orders();
					order.setOrderid(orderidcounter);
					order.setProductid(p.getProductid());
					order.setQuantity(k.getQuantity());
					order.setTotalprice(p.getProductprice()*k.getQuantity());
					order.setNow(order.getNow());
					p.setProductquantity(p.getProductquantity()-k.getQuantity());
					ordersrepo.save(order);
					orderlist.add(order);
				}
			}
		}
		
		double total=0;
		int quan=0;
		for(Orders o:orderlist) {
			total+=o.getTotalprice();
			quan+=o.getQuantity();
		}
		
		idvalue.setQuantity(quan);
		idvalue.setTotalordervalue(total);
		totalorderrepo.save(idvalue);
		return idvalue;
	}
}

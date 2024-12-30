package com.phones.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phones.Models.Orders;

@Repository
public interface ordersRepo extends JpaRepository<Orders, Integer>{

	Orders save(Orders order);

}

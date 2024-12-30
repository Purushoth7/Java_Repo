package com.phones.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.phones.Models.Brand;
import com.phones.Models.Products;

@Repository
public interface repoproducts extends JpaRepository<Products, Integer> {

	List<Products> findByProductname(String productname);
}

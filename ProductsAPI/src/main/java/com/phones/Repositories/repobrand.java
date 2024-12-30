package com.phones.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phones.Models.Brand;

@Repository
public interface repobrand extends JpaRepository<Brand, String> {

	List<Brand> findByBrandname(String brandname);
}

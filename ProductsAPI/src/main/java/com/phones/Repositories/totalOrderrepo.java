package com.phones.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phones.Models.orderidandvalue;

@Repository
public interface totalOrderrepo extends JpaRepository<orderidandvalue, Integer> {

}

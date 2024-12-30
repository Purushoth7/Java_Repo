package com.phones.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phones.Models.users;

@Repository
public interface UserRepo extends JpaRepository<users, Integer> {

    users findByUsername(String username);
	
	users findByEmailid(String emailid);
}

package com.example.country.respositaries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.country.beans.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}

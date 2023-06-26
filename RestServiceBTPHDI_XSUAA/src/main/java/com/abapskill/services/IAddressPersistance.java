package com.abapskill.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abapskill.entities.Address;

public interface IAddressPersistance extends JpaRepository<Address, String>{
	
}

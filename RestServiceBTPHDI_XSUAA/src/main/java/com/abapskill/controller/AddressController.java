package com.abapskill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abapskill.entities.Address;
import com.abapskill.services.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	AddressService service;
	
	@RequestMapping("/address")
	public List<Address> getAddresses(){
	  return service.getAddresses();	
	}
	
	@PostMapping("/address")
	public Address createAddress(@RequestBody Address payload) {
		return service.postAddress(payload);
	}
}

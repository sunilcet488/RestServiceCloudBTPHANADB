package com.abapskill.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abapskill.entities.Address;

@Component
public class AddressService {
	
	@Autowired
	IAddressPersistance address;
	
	public List<Address> getAddresses(){
		return address.findAll();
	}
	
	public Address postAddress(Address payload) {
		return address.save(payload);
	}

}

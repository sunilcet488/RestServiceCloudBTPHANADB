package com.abapskill.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abapskill.entities.Vendor;

@Component
public class VendorService {
	
	@Autowired
	IVendorPersistence vendor;
	
	public List<Vendor> getAllVendors(){
		return vendor.findAll();
	}
	
	public Optional<Vendor> getVendorById(String vendorCode) {
		return vendor.findById(vendorCode);
	}
	
	public List <Vendor> searchVendorByName(String name){
		return vendor.findByname(name);
	}
	
	public List <Vendor> getVendorsByName(String name){
		return vendor.lookupByName(name);
	}
	
	
	public Vendor createVendor(Vendor vendorobj) {
		return vendor.save(vendorobj);
	}
	
	public Vendor updateVendor(Vendor vendorobj) {
		Optional <Vendor> myVendor = vendor.findById(vendorobj.getVendorId());
		if(!myVendor.isPresent()) {
			return new Vendor();
		}
		return vendor.save(vendorobj);
	}
	
	public String deleteVendor(String vendorId) {
		vendor.deleteById(vendorId);
		return "Deleted";
	}
		

}

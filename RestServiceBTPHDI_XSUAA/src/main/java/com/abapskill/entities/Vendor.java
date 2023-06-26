package com.abapskill.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "VENDOR")
public class Vendor {
	
	
	@Id
	@GeneratedValue(generator = "vendorUUID")
	@GenericGenerator(name = "vendorUUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(nullable = false,name = "VENDOR_ID")
	public String vendorId;
	@Column(nullable = false,name = "NAME")
	public String name;
	@Column(nullable = false,name = "WEBSITE")
	public String website;
	@Column(nullable = false,name = "EMAIL")
	public String email;
	@Column(nullable = false,name = "STATUS")
	public String status;
	@Column(nullable = false,name = "GSTNO")
	public String gstNo;
	
	// Create a association with Address data 
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "Vendor",referencedColumnName = "VENDOR_ID")
	public List<Address> addresses = new ArrayList<>();

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	public Vendor() {
		
	}
	
	public Vendor(String vendorId, String name, String website, String email, String status, String gstNo) {
		super();
		this.vendorId = vendorId;
		this.name = name;
		this.website = website;
		this.email = email;
		this.status = status;
		this.gstNo = gstNo;
	}


	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	
	
	
	
	
	


}

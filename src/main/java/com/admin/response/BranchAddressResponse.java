package com.admin.response;

import java.io.Serializable;

public class BranchAddressResponse implements Serializable{

	private static final long serialVersionUID = -8110259526520862977L;

	private String fullAddress;

    private String city;
    
    private String state;
    
    private String pincode;
    
    private String country;

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
    
}

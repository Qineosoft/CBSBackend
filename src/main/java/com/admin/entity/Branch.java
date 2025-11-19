package com.admin.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "branch")
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "branch_type")
	private String branchType;

	@Column(name = "branch_id")
	private String branchId;

	@Column(name = "brnach_name")
	private String branchName;

	@Column(name = "ifsc_code")
	private String ifscCode;

	@Column(name = "location")
	private String location;

	@Column(name = "operating_hours")
	private String operatingHrs;

	@Column(name = "date_opened")
	private Date dateOpened;

	@Column(name = "status")
	private String status;
	
	@Column(name = "service_available")
	private List<String> services;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private BranchAddress branchAddress;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_id")
	private BranchContact branchContact;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOperatingHrs() {
		return operatingHrs;
	}

	public void setOperatingHrs(String operatingHrs) {
		this.operatingHrs = operatingHrs;
	}

	public Date getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BranchAddress getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(BranchAddress branchAddress) {
		this.branchAddress = branchAddress;
	}

	public BranchContact getBranchContact() {
		return branchContact;
	}

	public void setBranchContact(BranchContact branchContact) {
		this.branchContact = branchContact;
	}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}

}

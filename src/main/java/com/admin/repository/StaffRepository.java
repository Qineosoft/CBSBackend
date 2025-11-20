package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{

	@Query("SELECT s.staffId FROM Staff s ORDER BY s.id DESC LIMIT 1")
	public String finRecentStaffId();

}

package com.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.admin.entity.Staff;

import jakarta.transaction.Transactional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{

	@Query("SELECT s.staffId FROM Staff s ORDER BY s.id DESC LIMIT 1")
	public String finRecentStaffId();

	@Transactional
	@Modifying
	@Query("UPDATE Staff s SET s.status = :inactive WHERE s.id = :id")
	public int deleteStaff(@Param("id") Long id, @Param("inactive") String inactive);

	@Query("SELECT s FROM Staff s WHERE s.status = :active")
	public List<Staff> findAllActiveStaff(@Param("active") String active);

}

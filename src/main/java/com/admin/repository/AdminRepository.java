package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.admin.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	@Query("select a from Admin a where a.email = :email")
	public Admin findByEmail(@Param("email") String email);

	@Query("SELECT a.adminId FROM Admin a ORDER BY a.id DESC LIMIT 1")
	public String generateId();

//	@Query("SELECT a.otp FROM Admin a  WHERE a.adminId = :adminId")
//	public String findOtpByid(@Param("adminId") String adminId);

}

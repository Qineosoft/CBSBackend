package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.admin.entity.Admin;

import jakarta.transaction.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	@Query("select a from Admin a where a.email = :email")
	public Admin findByEmail(@Param("email") String email);

	@Query("SELECT a.adminId FROM Admin a ORDER BY a.id DESC LIMIT 1")
	public String generateId();

//	@Modifying
//	@Transactional
//	@Query("UPDATE Admin a SET a.otp = :otp WHERE a.id = :id")
//	public int updateOtp(@Param("otp") String otp,@Param("id") Long id);

//	@Query("SELECT a.otp FROM Admin a  WHERE a.adminId = :adminId")
//	public String findOtpByid(@Param("adminId") String adminId);

}

package com.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.admin.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{

	@Query("SELECT b FROM Branch b where b.status = :status")
	public List<Branch> findAllBranchWithActiveStatus(@Param("status") String status);
	
	@Query("SELECT b FROM Branch b WHERE b.branchId = :branchId")
	public Branch findByBranchId(@Param("branchId") String branchId);
	
	@Query("""
		    SELECT b FROM Branch b 
		    LEFT JOIN b.branchContact bc
		    LEFT JOIN b.branchAddress ba
		    WHERE 
		        b.branchId = :branchId
		        OR b.ifscCode = :ifscCode
		        OR bc.managerEmail = :managerEmail
		        OR bc.managerPhone = :managerPhone
		        OR bc.branchContactNum = :branchContactNum
		        OR bc.branchEmail = :branchEmail
		        OR (
		            :branchType = 'HEAD'
		            AND b.branchType = 'HEAD'
		            AND ba.state = :state
		        )
		    """)
		public List<Branch> findDuplicates(
		        @Param("branchId") String branchId,
		        @Param("ifscCode") String ifscCode,
		        @Param("managerEmail") String managerEmail,
		        @Param("managerPhone") String managerPhone,
		        @Param("branchContactNum") String branchContactNum,
		        @Param("branchEmail") String branchEmail,
		        @Param("branchType") String branchType,
		        @Param("state") String state
		);

	
}

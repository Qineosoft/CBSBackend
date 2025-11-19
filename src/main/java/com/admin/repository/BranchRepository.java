package com.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{

	@Query("SELECT b FROM Branch b where b.status = :ACTIVE")
	public List<Branch> findAllBranchWithActiveStatus();

}

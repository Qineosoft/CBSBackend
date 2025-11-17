package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{

}

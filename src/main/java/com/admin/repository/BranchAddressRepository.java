package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.entity.BranchAddress;

@Repository
public interface BranchAddressRepository extends JpaRepository<BranchAddress, Long>{

}

package com.lms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.lms.model.Branch;



@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
 
	//create branch
//		 public Branch createBranch(Branch saveBranch)throws Exception;
//	@Query(value="select  *  from branch_Master  bm ",nativeQuery=true)	
	 

		 //get all Branch
		 public List<Branch> findAll();
		 
		 
		 //get branch By id
		 public Branch getBranchById(Long branchId);


		public Optional<Branch> findById(Long branchId);
}

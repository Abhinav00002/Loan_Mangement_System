package com.lms.repo;

import java.util.List;
 
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.lms.model.address.Cluster;
 

@Repository
public interface ClusterRepository extends JpaRepository<Cluster, Integer> {
	
	
//	@Query(value="select  *  from cluster_master  cm ",nativeQuery=true)	
	public List<Cluster> findAll();
	
	//get Cluster By ClusterId
	public Cluster getClusterById(Integer clusterId);

}

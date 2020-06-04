package com.mindtree.yoyogift.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.yoyogift.entity.FeedBack;
@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Integer>{
	
	
	@Query("select f from FeedBack f where f.user.mailId=?1 and f.product.productId= ?2")
	public Optional<FeedBack> getUserFeedback(String mailId,int productId);
	
	


}

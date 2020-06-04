package com.mindtree.yoyogift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.yoyogift.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query(nativeQuery = true,value="select * from user where mail_id=?1")
	public User findByMailId(String mailId);
}

package com.mindtree.yoyogift.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mindtree.yoyogift.entity.Gift;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Integer> {

	@Query("Select gift from Gift gift where gift.receivedGiftUser.mailId=:mailId")
	List<Gift> getAllGiftsForUser(@Param("mailId") String mailId);

	@Query("Select gift from Gift gift where gift.sentGiftUser.mailId=:mailId")
	List<Gift> getAllSentGiftsForUser(@Param("mailId") String mailId);
}

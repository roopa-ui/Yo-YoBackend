package com.mindtree.yoyogift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.yoyogift.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}

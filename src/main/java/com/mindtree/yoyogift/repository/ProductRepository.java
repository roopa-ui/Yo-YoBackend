package com.mindtree.yoyogift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.yoyogift.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}

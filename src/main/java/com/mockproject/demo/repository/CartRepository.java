package com.mockproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.demo.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}

package com.mockproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.demo.model.CartLineItem;

@Repository
public interface CartLineItemRepository extends JpaRepository<CartLineItem, Long> {

}

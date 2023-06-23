package com.mockproject.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mockproject.demo.model.Address;
import com.mockproject.demo.model.CartLineItem;
import com.mockproject.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByName(String name);


}

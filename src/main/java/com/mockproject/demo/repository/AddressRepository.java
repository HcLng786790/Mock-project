package com.mockproject.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.demo.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	Optional<Address> findByIdAndUserId(long id , long userId);
}

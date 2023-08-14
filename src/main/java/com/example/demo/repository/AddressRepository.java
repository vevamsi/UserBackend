package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	 @Query(value="SELECT a FROM Address a WHERE a.user.user_id = :userId",nativeQuery=true)
	Address findByUserId(@Param("userId")int userId);
}


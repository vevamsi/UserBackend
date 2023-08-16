package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	//@Query("SELECT u FROM users u WHERE u.email = :email")
	//User findByEmail(@Param("email")String email);
	@Query(value="SELECT * FROM users WHERE email = :email LIMIT 1",nativeQuery=true)
	User findByEmail(@Param("email")String email);
	
}

package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserAuthDetails;

@Repository
public interface UserCredentialRepository  extends JpaRepository<UserAuthDetails,Integer> {
	
	UserAuthDetails findByUserEmail(String email);
	@Transactional
	  @Modifying
	    @Query(value="UPDATE user_details up SET up.password = :newPassword WHERE up.user.email = :email",nativeQuery=true)
	    void updatePasswordByEmail(@Param("email") String email, @Param("newPassword") String newPassword);
	 @Query(value=" select * from user_details up where up.user_id= :userId",nativeQuery=true)
	UserAuthDetails findByuserId(@Param("userId")int userId);
}

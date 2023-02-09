package com.example.demo.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

	User findByEmail(String email);
	
	Optional<User> findById(UUID id);
	
//	@Modifying
//	@Query("update Customer c set c.name: ")
//	User saveWhereId(int id,User user);
	
	@Transactional
	@Modifying
	@Query("update Customer c set c.name = :name where c.id = :id")
	public int updateName(@Param("id")UUID id,@Param("name") String name);
	
	@Transactional
	@Modifying
	@Query("update Customer c set c.address = :address where c.id = :id")
	public int updateAddress(@Param("id")UUID id,@Param("address") String address);
	
	@Transactional
	@Modifying
	@Query("update Customer c set c.phone = :phone where c.id = :id")
	public int updatePhone(@Param("id")UUID id,@Param("phone") long phone);
	
	@Transactional
	@Modifying
	@Query("update Customer c set c.password = :password where c.id = :id")
	public int updatePassword(@Param("id")UUID id,@Param("password") String password);
	
	@Transactional
	@Modifying
	@Query("update Customer c set c.roles = :roles where c.id = :id")
	public int updateRoles(@Param("id")UUID id,@Param("roles") String roles);



}

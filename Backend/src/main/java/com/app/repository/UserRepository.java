package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	 Optional<UserEntity> findByEmailAndPassword(String email, String password);
	 
	 @Query("select u from UserEntity u where u.email=?1")
	 Optional<UserEntity> findByEmail(String email);
	 
	//to fetch all only users using join on roles
	 @Query("select ue from UserEntity ue join ue.userRoles ur where ur.id=1")
	 List<UserEntity> findUserRoles();
	 
	 
}

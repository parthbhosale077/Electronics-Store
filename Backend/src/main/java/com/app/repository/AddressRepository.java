package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entities.UserAddress;
import com.app.entities.UserEntity;

public interface AddressRepository extends JpaRepository<UserAddress, Long> {
	List<UserAddress> findByUser(UserEntity user);
}

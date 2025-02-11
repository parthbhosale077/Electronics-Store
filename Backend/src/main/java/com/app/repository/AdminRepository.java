package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.UserEntity;

public interface AdminRepository extends JpaRepository<UserEntity, Long>
{

}

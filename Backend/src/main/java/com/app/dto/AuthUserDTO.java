package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import com.app.entities.RoleEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthUserDTO {
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String gender;
	private Set<RoleEntity> roles = new HashSet<>();
}

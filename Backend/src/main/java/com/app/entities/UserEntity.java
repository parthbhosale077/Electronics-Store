package com.app.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {
	
	//first_name	last_name	email	password	phone	gender
	@NotBlank(message = "first name is required")
	@Column(length = 25, nullable = false)
	private String firstName;
	@NotBlank(message = "last name is required")
	@Column(length = 25, nullable = false)
	private String lastName;
	@Column(length = 25, nullable = false)
//	@Pattern(regexp = " /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$/", message = "Invalid email")
	private String email;
	@Column(length = 75, unique = true, nullable = false)
	private String password;
	@Column(length = 13, unique = true, nullable = false)
	private String phone;
	@Column(length = 10, nullable = false)
	private String gender;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp joinedOn;

	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", 
	joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> userRoles = new HashSet<>();
}

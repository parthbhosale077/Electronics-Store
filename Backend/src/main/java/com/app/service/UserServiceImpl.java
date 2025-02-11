package com.app.service;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.AuthUserDTO;
import com.app.dto.CreatedResponse;
import com.app.dto.UserAddressDTO;
import com.app.dto.UserDTO;
import com.app.entities.UserAddress;
import com.app.entities.UserEntity;
import com.app.repository.AddressRepository;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private ModelMapper mapper;
	// password enc
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AddressRepository addressRepo;
	
//	@Override
//	public LoginResponse authenticateUser(String email, String password) {
//		UserEntity user = userRepo.findByEmailAndPassword(email, password)
//				.orElseThrow(() -> new RuntimeException("Auth Failed!"));
//		
//		return new LoginResponse(user);
//	}


	
	@Override
	public CreatedResponse registerUser(UserDTO user) 
	{
		UserEntity userEntity = mapper.map(user, UserEntity.class);
		// 2. Map Set<UserRole : enum> ---> Set<Role :entity> n assign it to the
		// transient user entity
		
		userEntity.setUserRoles(roleRepo.findByRoleNameIn(user.getRoles()));
		
		System.out.println(userEntity);
		// 3. encode pwd
		userEntity.setPassword(encoder.encode(user.getPassword()));
		userEntity.setJoinedOn(Timestamp.valueOf(LocalDateTime.now()));
		// 4 : Save user details
		userRepo.save(userEntity);
		return new CreatedResponse("success");
	}
	
	
	@Override
	public CreatedResponse updateAccountDetails(Long userId, UserEntity accountdetails) {
		UserEntity user = userRepo.findById(userId)
				 .orElseThrow(() -> 
				 new RuntimeException("User not found for this id :: " + userId));
		user.setFirstName(accountdetails.getFirstName());
		user.setLastName(accountdetails.getLastName());
	    user.setPhone(accountdetails.getPhone());
	    //user.setUserRoles(accountdetails.getUserRoles());
	    userRepo.save(user);
		return new CreatedResponse("success");
	}
	
	@Override
	public CreatedResponse insertAddress(UserAddressDTO address) {
		UserAddress add = mapper.map(address, UserAddress.class);
		UserEntity user = userRepo.findById(address.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
		add.setUser(user);
		
		System.out.println("in service class");
		addressRepo.save(add);
		return new CreatedResponse("success");
	}
	
	@Override
	public CreatedResponse editAddress(UserAddressDTO address, Long addressId) {
		
		
		UserAddress persistentAddress = addressRepo.findById(addressId).orElseThrow(() -> new RuntimeException("Address not found"));
		persistentAddress.setAddressLine1(address.getAddressLine1());
		persistentAddress.setAddressLine2(address.getAddressLine2());
		persistentAddress.setCity(address.getCity());
		persistentAddress.setCountry(address.getCountry());
		persistentAddress.setPincode(address.getPincode());
		persistentAddress.setState(address.getState());
		System.out.println("in service class");
		addressRepo.save(persistentAddress);
		return new CreatedResponse("success");
	}


	@Override
	public AuthUserDTO getUserDetails(String email) 
	{
		UserEntity user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		System.out.println(user);
		AuthUserDTO authUser = mapper.map(user, AuthUserDTO.class);
		authUser.setUserId(user.getId());
		authUser.setRoles(user.getUserRoles());
		System.out.println(authUser);
		return authUser;
	}


	@Override
	public List<UserAddress> getUserAddress(Long userId) {
		UserEntity user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
		List<UserAddress> address = addressRepo.findByUser(user);
		return address;
	}
	
	public boolean existsByEmail(String email) {
		
		return userRepo.findByEmail(email).isPresent();
	}

}

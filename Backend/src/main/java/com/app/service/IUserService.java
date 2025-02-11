package com.app.service;

import java.util.List;

//import com.app.dto.LoginResponse;
import com.app.dto.UserAddressDTO;
import com.app.dto.UserDTO;
//import com.app.entities.DiscountDetails;
import com.app.entities.UserAddress;
import com.app.entities.UserEntity;
import com.app.dto.AuthUserDTO;
import com.app.dto.CreatedResponse;

public interface IUserService {
//	LoginResponse authenticateUser(String email, String password);
	
	CreatedResponse registerUser(UserDTO user);

	CreatedResponse updateAccountDetails(Long userId, UserEntity accountdetails);
    CreatedResponse insertAddress(UserAddressDTO address);
	AuthUserDTO getUserDetails(String email);
	List<UserAddress> getUserAddress(Long userId);
	CreatedResponse editAddress(UserAddressDTO address, Long addressId);
	boolean existsByEmail(String email);
}

package com.app.service;

import com.app.dto.User;
import com.app.dto.UserDTO;
import com.app.dto.UserRegResponse;

public interface IUserService {
	User getUserByEmail(String userEmail);
	UserRegResponse registerUser(UserDTO user);
	User getUserById(Integer userId);
}

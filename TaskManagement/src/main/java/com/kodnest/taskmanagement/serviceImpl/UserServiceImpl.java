package com.kodnest.taskmanagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.taskmanagement.entity.Users;
import com.kodnest.taskmanagement.payload.UserDto;
import com.kodnest.taskmanagement.repository.UserRepository;
import com.kodnest.taskmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto createUser(UserDto userDto) {
//		userDto is not an entity of users
//		converted userDto to Users
		Users user = userDtoToEntity(userDto);
		Users savedUser = userRepository.save(user);
//		converted User to userDto and return it
		return entityToUserDto(savedUser);
	}
	
	private Users userDtoToEntity(UserDto userDto) {
		
		Users users = new Users();
		users.setName(userDto.getName());
		users.setEmail(userDto.getEmail());
		users.setPassword(userDto.getPassword());
		
		return users;
	}
	
	private UserDto entityToUserDto(Users savedUser) {
		
		UserDto userDto = new UserDto();
		userDto.setId(savedUser.getId());
		userDto.setName(savedUser.getName());
		userDto.setEmail(savedUser.getEmail());
		userDto.setPassword(savedUser.getPassword());
		
		return userDto;
	}
}
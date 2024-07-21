package com.kodnest.taskmanagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.taskmanagement.entity.Task;
import com.kodnest.taskmanagement.entity.Users;
import com.kodnest.taskmanagement.exception.APIException;
import com.kodnest.taskmanagement.exception.TaskNotFound;
import com.kodnest.taskmanagement.exception.UserNotFound;
import com.kodnest.taskmanagement.payload.TaskDto;
import com.kodnest.taskmanagement.repository.TaskRepository;
import com.kodnest.taskmanagement.repository.UserRepository;
import com.kodnest.taskmanagement.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public TaskDto saveTask(long userid, TaskDto taskDto) {
		Users user = userRepository.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User ID %d not found", userid))
				);
		Task task = modelMapper.map(taskDto, Task.class);
		task.setUsers(user);
//		After setting the user, we are storing the data in DB
		Task savedTask = taskRepository.save(task);
		return modelMapper.map(savedTask, TaskDto.class);
	}

	@Override
	public List<TaskDto> getAllTasks(long userid) {
		Users user = userRepository.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User ID %d not found", userid))
				);
		
		List<Task> tasks = taskRepository.findAllByUsersId(userid);
		
		return tasks.stream().map(
				task -> modelMapper.map(task, TaskDto.class)
				).collect(Collectors.toList());
	}

	@Override
	public TaskDto getTask(long userid, long taskid) {
		Users users = userRepository.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User ID %d not found", userid))
				);
		
		Task task = taskRepository.findById(taskid).orElseThrow(
				() -> new TaskNotFound(String.format("Task ID %d not found", taskid))
				);
		if(users.getId() != task.getUsers().getId()) {
			throw new APIException(String.format("Task ID %d is not belongs to User ID %d", taskid, userid));
		}
		return modelMapper.map(task, TaskDto.class);
	}

	@Override
	public void deleteTask(long userid, long taskid) {
		Users users = userRepository.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User ID %d not found", userid))
				);
		
		Task task = taskRepository.findById(taskid).orElseThrow(
				() -> new TaskNotFound(String.format("Task ID %d not found", taskid))
				);
		if(users.getId() != task.getUsers().getId()) {
			throw new APIException(String.format("Task ID %d is not belongs to User ID %d", taskid, userid));
		}
//		delete the task
		taskRepository.deleteById(taskid);
	}
}
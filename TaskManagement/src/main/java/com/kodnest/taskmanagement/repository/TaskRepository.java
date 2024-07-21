package com.kodnest.taskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.taskmanagement.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findAllByUsersId(long userid);
}
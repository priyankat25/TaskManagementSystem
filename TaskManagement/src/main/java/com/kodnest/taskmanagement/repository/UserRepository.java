package com.kodnest.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.taskmanagement.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
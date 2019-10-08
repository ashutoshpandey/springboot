package com.spr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spr.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}

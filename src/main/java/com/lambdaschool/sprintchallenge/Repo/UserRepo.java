package com.lambdaschool.sprintchallenge.Repo;


import com.lambdaschool.sprintchallenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer>

{
     User findByUsernameIgnoreCase(String username);
}
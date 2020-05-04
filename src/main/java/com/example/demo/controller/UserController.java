package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("all")
	public List<User> getAll(){
		return userService.getAll();
	}
	
	
	@RequestMapping(value = "/",method = RequestMethod.POST)
	public User save(@RequestBody User user) {
		return userService.save(user);
	}
	
	@RequestMapping(value = "/{id}")
    public Optional<User> getEntityById(@PathVariable String id) {
        return userService.findUserById(id);              
    }
	
}

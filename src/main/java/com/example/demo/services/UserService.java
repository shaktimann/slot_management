package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repos.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User save(User user) {
		return repository.save(user);
	}

	public List<User> getAll() {
		Iterable<User> iterable = repository.findAll();
		List<User> users = new ArrayList<>();
		iterable.forEach(new Consumer<User>() {
			@Override
			public void accept(User t) {
				users.add(t);
			}

		});
		return users;
	}
	
	public Optional<User> findUserById(String id) {
        return repository.findById(id);
    }
	
	public Optional<User> findUserByEmail(String email) {
	    return repository.findByEmail(email);
	}
	
	public Optional<User> findUserByName(String name) {
        return repository.findByName(name);
    }

}

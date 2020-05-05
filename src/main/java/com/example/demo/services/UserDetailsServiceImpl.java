package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.User;

public class UserDetailsServiceImpl implements UserDetailsService{

	 @Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    /*Here we are using dummy data, you need to load user data from
	     database or other third party application*/
	    User user = findUserbyUername(username);

	    String[]  roles = new String[2];
	    roles[0] = "USER";
	    roles[1] = "ADMIN";
	    UserBuilder builder = null;
	    if (user != null) {
	      builder = org.springframework.security.core.userdetails.User.withUsername(username);
	      builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
	      builder.roles(roles);
	    } else {
	      throw new UsernameNotFoundException("User not found.");
	    }

	    return builder.build();
	  }

	  private User findUserbyUername(String username) {
	    if(username.equalsIgnoreCase("admin")) {
	    	List<String> users  = new ArrayList<String>();
	    	users.add("USER");
	      return User.builder().name(username).password("Lakhan@123").roles(users).build();
	    }
	    return null;
	  }
	
}

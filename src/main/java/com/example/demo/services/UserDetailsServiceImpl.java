package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
@Component
public class UserDetailsServiceImpl implements UserDetailsService{

	
	@Autowired
	private UserService userService;
	
	 @Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    /*Here we are using dummy data, you need to load user data from
	     database or other third party application*/
	    User user = findUserbyEmail(username);
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

	  private User findUserbyEmail(String email) {
	    	return userService.findUserByEmail(email).get();
	  }
	
}

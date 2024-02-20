package com.blog.user.UserService.security;


import com.blog.user.UserService.model.User;
import com.blog.user.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImple implements UserDetailsService{

	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userByUserName = this.userService.findByEmail(username);
		if(userByUserName == null) {
			throw new UsernameNotFoundException("could Not Found !!! ");
		}
		CustomUserDetail customUserDetail=new CustomUserDetail(userByUserName);
		return customUserDetail;
	}


}

package com.hexaware.fastx.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hexaware.fastx.entities.Admin;
import com.hexaware.fastx.entities.BusOperator;
import com.hexaware.fastx.entities.User;
import com.hexaware.fastx.repository.AdminRepository;
import com.hexaware.fastx.repository.BusOperatorRepository;
import com.hexaware.fastx.repository.UserRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BusOperatorRepository busOperatorRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> adminOptional = adminRepo.findByAdminUsername(username);
		
		if(adminOptional.isPresent()) {
			Admin admin = adminOptional.get();
			return new AdminUserDetails(admin);
		}
		
		Optional<BusOperator> busOperatorOptional = busOperatorRepo.findByOperatorUsername(username);
		if(busOperatorOptional.isPresent()) {
			BusOperator busOperator = busOperatorOptional.get();
			return new BusOperatorUserDetails(busOperator);
		}
		
		Optional<User> userOptional = userRepo.findByUsername(username);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			return new UserDetailsImp(user);
		}
		throw new UsernameNotFoundException("User not found for username: " + username);
	}

}

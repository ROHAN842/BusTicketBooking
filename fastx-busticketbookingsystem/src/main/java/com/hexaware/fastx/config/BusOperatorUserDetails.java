package com.hexaware.fastx.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hexaware.fastx.entities.BusOperator;

public class BusOperatorUserDetails implements UserDetails {
	
	private String operatorUsername;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public BusOperatorUserDetails(BusOperator busOperator) {
		operatorUsername = busOperator.getOperatorUsername();
		password = busOperator.getOperatorPassword();
		authorities = Arrays.stream(busOperator.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return operatorUsername;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

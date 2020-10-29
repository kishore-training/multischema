package com.udemy.multischema.security;

import com.udemy.multischema.model.DoctorUser;
import com.udemy.multischema.service.DoctorUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;


import java.util.Arrays;
import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private DoctorUserRepository doctorUserRepository;

	/**
	 * Fetch and return the details of the logged in user. This User is automatically stored in Spring SecurityContextHolder Threadlocal
	 * object and spring checks that a valid user and password for the hospital tenant is present. If no users found Invalid User exception is thrown
	 * @param userNameAndHospitalString A string which has both the user and hospital
	 * @return the logged in user object
	 * @throws UsernameNotFoundException If the logged in user and password doesnt match
	 */
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


		List<DoctorUser> doctorUsers = doctorUserRepository.findByUserName(userName);
		DoctorUser doctorUser = null;
		if(doctorUsers == null || doctorUsers.isEmpty()){
			throw new UsernameNotFoundException("Invalid user email or password.");
		}else{
			doctorUser = doctorUsers.get(0);

		}

		return new org.springframework.security.core.userdetails.User(doctorUser.getUserName(),(doctorUser.getPassword()), getAuthority(doctorUser));
	}

	private List<SimpleGrantedAuthority> getAuthority(DoctorUser doctorUser) {

		return Arrays.asList(new SimpleGrantedAuthority(doctorUser.getUserTypeEnum().name()));
	}


}

package com.kodgemisi.assignment.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kodgemisi.assignment.domains.Role;
import com.kodgemisi.assignment.domains.User;
import com.kodgemisi.assignment.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

  @Autowired
  private UserRepository userRepository;
  
  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

  	User user = userRepository.findAuthenticatedUserByEmail(email);
    if(user != null){
      Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
      for (Role role : user.getRoles()){
          grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
      }
      return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }else{
    	throw new UsernameNotFoundException("User not found");
    }
  }

}

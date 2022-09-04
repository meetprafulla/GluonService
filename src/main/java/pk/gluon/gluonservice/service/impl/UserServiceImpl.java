package pk.gluon.gluonservice.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pk.gluon.gluonservice.entity.Role;
import pk.gluon.gluonservice.entity.User;
import pk.gluon.gluonservice.repository.RoleRepository;
import pk.gluon.gluonservice.repository.UserRepository;
import pk.gluon.gluonservice.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername "+username);
		User user = userRepository.findByEmail(username);
		System.out.println("loadUserByUsername "+user);
		if (user == null) {
			throw new UsernameNotFoundException("User not found in the database");
		} else {
			Collection<SimpleGrantedAuthority> authrorities = new ArrayList<SimpleGrantedAuthority>();
			user.getRoles().forEach(eachRole -> {
				System.out.println("eachRole "+eachRole.getRole());
				authrorities.add(new SimpleGrantedAuthority(eachRole.getRole()));
			});
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authrorities);
		}
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		User user = userRepository.findByEmail(username);
		Role role = roleRepository.findByRole(rolename);
		user.getRoles().add(role);
		userRepository.save(user);
	}

	@Override
	public User getUser(String username) {
		return userRepository.findByEmail(username);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}

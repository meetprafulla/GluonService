package pk.gluon.gluonservice.service;

import java.util.List;

import pk.gluon.gluonservice.entity.Role;
import pk.gluon.gluonservice.entity.User;

public interface UserService {
	User saveUser(User user);

	Role saveRole(Role role);

	void addRoleToUser(String username, String rolename);

	User getUser(String username);
	
	List<User> getAllUsers();
}

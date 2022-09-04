package pk.gluon.gluonservice.dto;

import java.io.Serializable;

public class UserRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8002317243959563702L;
	
	public String getUsername() {
		return username;
	}
	public String getRolename() {
		return rolename;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	private String username;
	private String rolename;
	
}

package pk.gluon.gluonservice.api.request;

import java.io.Serializable;

public class Token implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5420724508483373472L;

	private String access_token;
	private String refresh_token;

	public String getAccess_token() {
		return access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

}

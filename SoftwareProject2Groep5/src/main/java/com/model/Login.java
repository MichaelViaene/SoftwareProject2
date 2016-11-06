package com.model;

public class Login {
	//login_id(int11),username(vchar45),passwoord(vchar45),bevoegdheid(int11),salt(vchar45)

	public enum Bevoegdheid{
	ADMIN(1), WERKNEMER(2);
	private int value;
	Bevoegdheid(int value){this.value = value;}
	}

	private int login_id;
	private String username;
	private String password;
	private Bevoegdheid bevoegdheid;
	private String salt;
	
	@Override
	public String toString() {
		return "Login [login_id=" + login_id + ", username=" + username + ", password=" + password + ", bevoegdheid="
				+ bevoegdheid + ", salt=" + salt + "]";
	}
	
	public Login() {
		super();
	}

	public Login(int login_id, String username, String password, Bevoegdheid bevoegdheid, String salt) {
		super();
		this.login_id = login_id;
		this.username = username;
		this.password = password;
		this.bevoegdheid = bevoegdheid;
		this.salt = salt;
	}

	public int getLogin_id() {
		return login_id;
	}

	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Bevoegdheid getBevoegdheid() {
		return bevoegdheid;
	}
	
	//public int getBevoegdheidInt() {return bevoegdheid.ordinal();}
	public int getBevoegdheidInt() {
		return bevoegdheid.value;
	}
	public void setBevoegdheid(int bevoegdheid) {
		for (Bevoegdheid b : Bevoegdheid.values()){
			if (b.value == bevoegdheid)
			this.bevoegdheid = b;
		}
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bevoegdheid == null) ? 0 : bevoegdheid.hashCode());
		result = prime * result + login_id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (bevoegdheid != other.bevoegdheid)
			return false;
		if (login_id != other.login_id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}

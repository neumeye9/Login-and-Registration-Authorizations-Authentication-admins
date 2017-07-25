package com.krista.auth.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users_roles")
public class User_Role {
	
	@Id


	@Column
	private Long user_id;
	
	@Column
	private Long role_id;

	public User_Role() {
	}
	
	public User_Role(Long user_id, Long role_id) {
		this.user_id = user_id;
		this.role_id = role_id;	
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
}

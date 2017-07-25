package com.krista.auth.models;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    @Size(min = 2)
    private String first_name;
    
    @Column
    @Size(min = 2)
    private String last_name;
    
    @Column
    @Size(min=6)
    private String email;
    
    @Column
    @Size(min=10)
    private String password;
    
    @Transient
    private String passwordConfirmation;
    
    @Column
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date created_at;
    
    @Column
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date updated_at;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    		name = "users_roles",
    		joinColumns = @JoinColumn(name = "user_id"),
    		inverseJoinColumns = @JoinColumn(name = "role_id")
    		)
    
    private List<Role> roles;
    
    
    		
    
    public User() {
    }
    
    public User(String first_name, String last_name, String email, String password, String passwordConfirmation, Date created_at, Date updated_at ) {
    		this.first_name = first_name;
    		this.last_name = last_name;
    		this.email = email;
    		this.password = password;
    		this.passwordConfirmation = passwordConfirmation;
    		this.created_at = new Date();
        this.updated_at = new Date();
    }
    
    @PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public boolean isAdmin(){
		for(Role role:this.roles){			
			if(role.getName().equals("ROLE_ADMIN")){return true;}
		}
		return false;
	}
	public boolean isSuperAdmin(){
		for(Role role:this.roles){			
			if(role.getName().equals("ROLE_SUPERADMIN")){return true;}
		}
		return false;
	}
	
}



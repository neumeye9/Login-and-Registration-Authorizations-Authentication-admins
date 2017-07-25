package com.krista.auth.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krista.auth.models.Role;

import com.krista.auth.repositories.RoleRepository;
import com.krista.auth.repositories.User_RoleRepository;

@Service
public class RoleService {
	
private RoleRepository roleRepository;

	
	public RoleService(RoleRepository roleRepository, User_RoleRepository user_RoleRepository){
		this.roleRepository = roleRepository;
	
	}
	
	public List<Role> allRoles(){
		return roleRepository.findAll();
	}
	
	public Role findByName(String name){
		return roleRepository.findByName(name);
	}
	
	public void create(Role role){
		roleRepository.save(role);
	}
	
//	public void createJoin(User_Role user_Role) {
//		user_RoleRepository.save(user_Role);
//	}
	
	public void update(Role role){
		roleRepository.save(role);
	}
	
	public void destroy(Long id){
		roleRepository.delete(id);
		}
}



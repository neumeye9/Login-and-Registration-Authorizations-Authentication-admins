package com.krista.auth.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.krista.auth.models.Role;
import com.krista.auth.models.User;
import com.krista.auth.repositories.RoleRepository;
import com.krista.auth.repositories.UserRepository;


@Service

public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)   {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
//    1
    public void saveWithUserRole(User user) {
       List<Role> userRoles = user.getRoles();
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       userRoles.add(roleRepository.findByName("ROLE_USER"));
       userRepository.save(user);
    }
  
   // 2 
    public void saveUserWithAdminRole(User user) {
    	   List<Role> userRoles = new ArrayList<>();
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       userRoles.add(roleRepository.findByName("ROLE_ADMIN"));
       user.setRoles(userRoles);
       userRepository.save(user);
   }  
    
    public void update(User user)
    		{userRepository.save(user);
    	}
    
    // 3
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public List<User> allUsers(){
    		return (List<User>) userRepository.findAll();
    	}
    
	public User getById(long id){
		return userRepository.findOne(id);
	}
	
	public void destroy(long id){
		userRepository.delete(id);
	}
	
	

	
}
package com.krista.auth.controllers;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.krista.auth.models.Role;
import com.krista.auth.models.User;
import com.krista.auth.models.User_Role;
import com.krista.auth.services.RoleService;
import com.krista.auth.services.UserService;
import com.krista.auth.validator.UserValidator;

@Controller
public class Users {
	
	private UserService userService;
    private UserValidator userValidator;
    private RoleService roleService;
    
    

    public Users(UserService userService, UserValidator userValidator, RoleService roleService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.roleService = roleService;
    }
    
    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout,@ModelAttribute("user") User user, Model model) {

    		if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successfull!");
        }
        model.addAttribute("user",new User());
 
        return "loginAndRegPage";
    }
    
    @RequestMapping("/registration")
    public String registerForm(@Valid @ModelAttribute("user") User user) {
        return "loginAndRegPage";
    }
    
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, @ModelAttribute("role") Role role, @ModelAttribute("user_role")User_Role user_Role) {
    		
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "loginAndRegPage";
        }
        
     
        
        if( roleService.findByName("ROLE_ADMIN").getUsers().size() < 1) {
   
        		userService.saveUserWithAdminRole(user);
        }
        else{
        		userService.saveWithUserRole(user);
        }
        
        return "redirect:/home";
    }
    
    
    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model) {
    		System.out.println("here");
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
        User user = userService.findByEmail(email);
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd, YYYY");
        Date date = new Date();
        Date created = user.getCreated_at();
        model.addAttribute("created", dateFormat.format(created));
        model.addAttribute("date", dateFormat.format(date));
        return "homePage";
    }
    
    @RequestMapping("/admin")
    public String admin(Principal principal, Model model) {
    		model.addAttribute("admin",userService.findByEmail(principal.getName()));
    		model.addAttribute("users",userService.allUsers());
    		return "adminPage";
    }
    
    @RequestMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
    		userService.destroy(id);
		return "redirect:/admin";
    }
    
    @RequestMapping("/admin/makeAdmin/{id}")
    public String makeAdmin(@PathVariable("id") Long id) {
    		User user = userService.getById(id);
    		List<Role> userRoles = user.getRoles();
    		userRoles.add(roleService.findByName("ROLE_ADMIN"));
    		userService.update(user);
    		System.out.println("Made user an admin");
    		return "redirect:/admin";
    }
    
    
    @RequestMapping("/logout")
    public String logout() {
    		return "loginAndRegPage";
    }
    
    
}
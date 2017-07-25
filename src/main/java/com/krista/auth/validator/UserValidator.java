package com.krista.auth.validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.krista.auth.models.User;


@Component
public class UserValidator implements Validator {
    
    //  1
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    private static final String EMAIL_REGEX_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;
	
	public UserValidator(){
		pattern = Pattern.compile(EMAIL_REGEX_PATTERN);
	}
	
    public boolean checkEmail(String email) {
    		matcher = pattern.matcher(email);
    		return matcher.matches();
    }
    
    // 2
    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            System.out.println(user.getPassword());
            errors.rejectValue("passwordConfirmation", "Match");
        }
        else if(!checkEmail(user.getEmail())){
        		System.out.println(user.getEmail());
			errors.rejectValue("email","Match");
		}
    }
}
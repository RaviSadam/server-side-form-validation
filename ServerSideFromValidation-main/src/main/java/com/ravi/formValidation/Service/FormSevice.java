package com.ravi.formValidation.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ravi.formValidation.Repository.UserRepo;
import com.ravi.formValidation.Entities.User;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class FormSevice {
	
	@Autowired
	UserRepo repo;
	public User insertUser(User user) {
		return repo.save(user);
	}
	public boolean isValidForm(User user,Model model){
		boolean valid=true;
		if(!ValidationUtils.isValidName(user.getName())) {
			valid=false;
			model.addAttribute("nameValid",false);
		}
		else {
			model.addAttribute("nameValid",true);
		}
		if(!ValidationUtils.isValidEmail(user.getEmail())) {
			valid=false;
			model.addAttribute("emailValid",false);
		}
		else
			model.addAttribute("emailValid",true);
		if(user.getPassword().length()!=10 && !ValidationUtils.isValidPhoneNumber(user.getPhone())) {
			valid=false;
			model.addAttribute("phoneValid",false);
		}
		model.addAttribute("phoneValid",true);
		
		if(!ValidationUtils.isValidName(user.getVillage())) {
			valid=false;
			model.addAttribute("villageValid",false);
		}
		else
			model.addAttribute("villageValid",true);
		if(!ValidationUtils.isValidName(user.getMandal())) {
			valid=false;
			model.addAttribute("mandalValid",false);
		}
		else
			model.addAttribute("mandalValid", true);
		if(!ValidationUtils.isValidName(user.getDistrict())) {
			valid=false;
			model.addAttribute("districtValid",false);
		}
		else
			model.addAttribute("districtValid",true);
		if(!ValidationUtils.isValidPass(user.getPassword(),model)){
			valid=false;
			model.addAttribute("passwordValid",false);
		}
		else
			model.addAttribute("passwordValid", false);
		if(valid){
			user.setPassword(ValidationUtils.encodePassword(user.getPassword()));
		}

		return valid && this.insertUser(user)!=null;
	}

}

class ValidationUtils {

    // Regular expressions for validation
    private static final String NAME_REGEX = "^[A-Za-z\\s]+$";
    private static final String PHONE_REGEX = "^[0-9]{10}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";


    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidPass(String password,Model model) {
		int num=0,special=0,small=0,cap=0;
		for(int i=0;i<password.length();i++){
			int ord=(int)password.charAt(i);
			if(ord>=48 && ord<=57){
				num+=1;
			}
			else if(ord>=65 && ord<=90){
				cap+=1;
			}
			else if(ord>=97 && ord<=122){
				small+=1;
			}
			else{
				special+=1;
			}
		}
		if(password.length() < 8 || password.length() > 16){
			model.addAttribute("passwordValid1",false);
		}
		else{
			model.addAttribute("passwordValid1",true);
		}
		if(small<1){
			model.addAttribute("passwordValid2",false);
		}
		else{
			model.addAttribute("passwordValid2",true);
		}
		if(cap<1){
			model.addAttribute("passwordValid3",false);
		}	
		else{
			model.addAttribute("passwordValid3",true);
		}
		if(special < 1){
			model.addAttribute("passwordValid4",false);
		}
		else
			model.addAttribute("passwordValid4",true);
        return  password.length()>=8 && password.length() <=16 && num>=1 && cap>=1 && small>=1 && special>=1;
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }   
	
	//password encoder
	public static String encodePassword(String password) {
        String salt = BCrypt.gensalt(12);
        String hashedPassword = BCrypt.hashpw(password, salt);
        return hashedPassword;
    }
}


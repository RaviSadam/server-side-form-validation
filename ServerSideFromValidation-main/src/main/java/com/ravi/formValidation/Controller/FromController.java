package com.ravi.formValidation.Controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ravi.formValidation.Entities.User;
import com.ravi.formValidation.Service.FormSevice;

@Controller
public class FromController {
	
	public final FormSevice formSevice;

	public FromController(FormSevice formSevice){
		this.formSevice=formSevice;
	}
	
	@GetMapping("/home")
	public String homePage(Model model) {
		model.addAttribute("nameValid",true);
		model.addAttribute("emailValid",true);
		model.addAttribute("phoneValid",true);
		model.addAttribute("villageValid",true);
		model.addAttribute("mandalValid",true);
		model.addAttribute("districtValid",true);
		model.addAttribute("passwordValid",true);
		model.addAttribute("passwordValid1",true);
		model.addAttribute("passwordValid2",true);
		model.addAttribute("passwordValid3",true);
		model.addAttribute("passwordValid4",true);
		return "home";
	}
	@PostMapping("/newRegister")
	public String successPage(@ModelAttribute User user,Model model){
		boolean b=formSevice.isValidForm(user,model);
		if(b){
			return "SuccessPage"; 
		}
		return "home";
	}
}

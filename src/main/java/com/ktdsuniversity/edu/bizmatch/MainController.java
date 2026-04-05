package com.ktdsuniversity.edu.bizmatch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String viewLoginBeforeMainPage() {
		
		return "main/mainpage";
	}
}

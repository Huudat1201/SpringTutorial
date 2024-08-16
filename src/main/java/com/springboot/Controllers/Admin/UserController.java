package com.springboot.Controllers.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	@RequestMapping("/logon")
	public String logon() {
		return "admin/logon";
	}
}

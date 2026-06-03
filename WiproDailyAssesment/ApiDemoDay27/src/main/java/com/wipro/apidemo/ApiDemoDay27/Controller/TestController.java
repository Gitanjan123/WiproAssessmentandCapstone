package com.wipro.apidemo.ApiDemoDay27.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.apidemo.ApiDemoDay27.Models.Boy;
import com.wipro.apidemo.ApiDemoDay27.Models.Girl;
import com.wipro.apidemo.ApiDemoDay27.Models.User;
import com.wipro.apidemo.ApiDemoDay27.Services.UserService;

@RestController
@RequestMapping("/wipro")
public class TestController {
	@Autowired 
	UserService service;
	@GetMapping("/welcome")
	String getMessage()
	{
		return "Welcome to wipro App!";
	}
	@GetMapping("/users")
	List<User>getUsers()
	{
		return service.getAllUser();
	}
	@PostMapping("/girl")
	User signupUser(@RequestBody Girl obj)
	{
		return service.saveUser(obj);
	}
	@PostMapping("/boy")
	User signupBoy(@RequestBody Boy obj)
	{
		return service.saveUserBoy(obj);
	}
}

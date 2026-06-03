package com.wipro.apidemo.ApiDemoDay27.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.apidemo.ApiDemoDay27.Models.Boy;
import com.wipro.apidemo.ApiDemoDay27.Models.Girl;
import com.wipro.apidemo.ApiDemoDay27.Models.User;
import com.wipro.apidemo.ApiDemoDay27.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	
	UserRepository repo;
	public User saveUser(Girl obj)
	{
		return repo.save(obj);
	}
	
	public User saveUserBoy(Boy obj)
	{
		return repo.save(obj);
	}
	public List<User>getAllUser()
	{
		return repo.findAll();
	}
}

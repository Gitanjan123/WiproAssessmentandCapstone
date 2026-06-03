package com.wipro.apidemo.ApiDemoDay27.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.apidemo.ApiDemoDay27.Models.User;





@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}

package com.wipro.WiproSpringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllUsers() {
        return jdbcTemplate.queryForList("SELECT * FROM users");
    }

    public int addUser(User obj) {
        String sql = "INSERT INTO users VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            obj.getName(), obj.getPassword(), obj.getEid(), obj.getSalary());
    }

    public Map<String, Object> getUserById(String id) {
        String sql = "SELECT * FROM users WHERE eid = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }
}
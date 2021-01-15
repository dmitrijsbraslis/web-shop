package com.app.dao;

import com.app.model.Registration;
import com.app.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void storeUser(Registration reg) {
        jdbcTemplate.update("INSERT INTO users (username, password, birth_date) VALUES (?, ?, ?)",
                reg.getUsername(), reg.getPassword(), reg.getBirthDate());
    }

    public List<Users> getUsers() {
        RowMapper<Users> rowMapper = (rs, rowNumber) -> mapUser(rs);
        return jdbcTemplate.query("SELECT * FROM users", rowMapper);
    }

    public List<Users> getUsersByUsername(String username) {
        RowMapper<Users> rowMapper = (rs, rowNumber) -> mapUser(rs);
        return jdbcTemplate.query("SELECT * FROM users WHERE username = ?", rowMapper, username);
    }

    private Users mapUser(ResultSet rs) throws SQLException {
        Users users = new Users();

        users.setId(rs.getInt("id"));
        users.setEmail(rs.getString("email"));
        users.setUsername(rs.getString("username"));
        users.setPassword(rs.getString("password"));

        return users;
    }
}

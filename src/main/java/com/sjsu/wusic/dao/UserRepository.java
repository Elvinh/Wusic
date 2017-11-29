package com.sjsu.wusic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sjsu.wusic.dao.ArtistRepository.ArtistMapper;
import com.sjsu.wusic.model.User;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User findById(int id) {
		return (User) jdbcTemplate.queryForObject("SELECT * FROM user WHERE user_id = ?", new Object[] {id}, new UserMapper());
	}
	
	class UserMapper implements RowMapper {
		
		@Override 
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("user_id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			
			return user;
		}
	}
	
}

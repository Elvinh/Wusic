package com.sjsu.wusic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sjsu.wusic.model.User;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User findByUsername(String username) {
		return (User) jdbcTemplate.queryForObject("SELECT * FROM users WHERE username = ?", new Object[] {username}, new UserMapper());
	}
	
	/**
	 * Who is following a given User 
	 * 
	 * @param username
	 * @return a List of Users following a given User  
	 */
	public List<User> getUserFollowers(String username) {

		List<User> userFollowers = new ArrayList<>();

		List<Map<String, Object>> maps =
				jdbcTemplate.queryForList("SELECT fr.name, fr.username FROM user_follows_user INNER JOIN users fg "
						+ "INNER JOIN users fr WHERE fg.username = following_id AND fr.username = follower_id "
						+ "AND fg.username = ?; ", username);

		for (Map<String, Object> map : maps) {
			User user = new User();
			user.setName((String) map.get(("name")));
			user.setUsername((String) map.get(("username")));

			userFollowers.add(user);
		}

		return userFollowers;
	}

	/**
	 * Who is a given User following 
	 * 
	 * @param username
	 * @return a List of Users following a user with a given id 
	 */
	public List<User> getUserFollowing(String username) {

		List<User> userFollowing = new ArrayList<>();

		List<Map<String, Object>> maps =
				jdbcTemplate.queryForList("SELECT fg.name, fg.username FROM user_follows_user INNER JOIN users fg "
						+ "INNER JOIN users fr WHERE fg.username = following_id AND fr.username = follower_id "
						+ "AND fr.username = ?; ", username);

		for (Map<String, Object> map : maps) {
			User user = new User();
			user.setName((String) map.get(("name")));
			user.setUsername((String) map.get(("username")));

			userFollowing.add(user);
		}

		return userFollowing;
	}	

	public List<User> getAllUsers() {

		List<User> users = new ArrayList<>();

		List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM users");

		for(Map<String, Object> map : maps) {
			User user = new User();
			user.setUsername((String) map.get("username"));
			user.setName((String) map.get("name"));

			users.add(user);
		}

		return users;
	}

	class UserMapper implements RowMapper {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setName(rs.getString("name"));
			user.setUsername(rs.getString("username"));

			return user;
		}
	}
	
}

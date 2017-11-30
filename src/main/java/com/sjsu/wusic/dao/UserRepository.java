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

import com.sjsu.wusic.dao.ArtistRepository.ArtistMapper;
import com.sjsu.wusic.model.Song;
import com.sjsu.wusic.model.User;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User findById(int id) {
		return (User) jdbcTemplate.queryForObject("SELECT * FROM user WHERE user_id = ?", new Object[] {id}, new UserMapper());
	}
	
	/**
	 * Who is following a given User 
	 * 
	 * @param id
	 * @return a List of Users following a given User  
	 */
	public List<User> getUserFollowers(int id) {
		
		List<User> userFollowers = new ArrayList<>();
		
        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList("SELECT fr.user_id, fr.name, fr.email FROM user_follows INNER JOIN user fg "
                		+ "INNER JOIN user fr WHERE fg.user_id = followingID AND fr.user_id = followerID "
                		+ "AND fg.user_id = ?; ", id);

        for (Map<String, Object> map : maps) {
        		User user = new User();
        		user.setId((Integer) map.get(("user_id")));
        		user.setName((String) map.get(("name")));
        		user.setEmail((String) map.get(("email")));
        		
        		userFollowers.add(user);
        }

        return userFollowers;
	}

	/**
	 * Who is a given User following 
	 * 
	 * @param id
	 * @return a List of Users following a user with a given id 
	 */
	public List<User> getUserFollowing(int id) {
		
		List<User> userFollowing = new ArrayList<>();
		
        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList("SELECT fg.user_id, fg.name, fg.email FROM user_follows INNER JOIN user fg "
                		+ "INNER JOIN user fr WHERE fg.user_id = followingID AND fr.user_id = followerID "
                		+ "AND fr.user_id = ?; ", id);

        for (Map<String, Object> map : maps) {
        		User user = new User();
        		user.setId((Integer) map.get(("user_id")));
        		user.setName((String) map.get(("name")));
        		user.setEmail((String) map.get(("email")));
        		
        		userFollowing.add(user);
        }

        return userFollowing;
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

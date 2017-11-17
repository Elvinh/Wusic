package com.sjsu.wusic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sjsu.wusic.model.Artist;

@Repository
public class ArtistRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Artist findById(int id) { 
		return (Artist) jdbcTemplate.queryForObject("SELECT * FROM artist where artist_id = ?", new Object[] {id}, new ArtistMapper());
	}
	
	class ArtistMapper implements RowMapper {
		
		@Override 
		public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
			Artist artist = new Artist();
			artist.setId(rs.getInt("artist_id"));
			artist.setName(rs.getString("name"));
			artist.setBirthdate(rs.getDate("birthdate"));
			artist.setHometown(rs.getString("hometown"));
			
			return artist;
		}
		
	}
	
}

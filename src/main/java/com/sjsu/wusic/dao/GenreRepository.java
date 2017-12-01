package com.sjsu.wusic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sjsu.wusic.model.Genre;

@Repository
public class GenreRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Genre findById(String genreName) {
		return (Genre) jdbcTemplate.queryForObject("SELECT name FROM genre WHERE name = ?", new Object[]{genreName}, new GenreMapper());
	}

	class GenreMapper implements RowMapper {
		
		@Override
		public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
			Genre genre = new Genre();
			genre.setName(rs.getString("name"));

			
			return genre;
		}
	}
}

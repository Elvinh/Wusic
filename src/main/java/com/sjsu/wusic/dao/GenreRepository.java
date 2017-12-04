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

import com.sjsu.wusic.model.Genre;

@Repository
public class GenreRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Genre findById(String genreName) {
		return (Genre) jdbcTemplate.queryForObject("SELECT name FROM genre WHERE name = ?", new Object[]{genreName}, new GenreMapper());
	}
	
	public List<Genre> genresByArtist(String artistId) {
		
		List<Genre> genresByArtist = new ArrayList<>();
		
		List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT genre.name "
				+ "FROM artist_genre_tag aig INNER JOIN artist INNER JOIN genre "
				+ "WHERE aig.artist_id = artist.artist_id "
				+ "AND aig.genre_name = genre.name "
				+ "AND artist.artist_id = ?", artistId);
		
		for(Map<String, Object> map : maps) {
			Genre genre = new Genre();
			genre.setName((String) map.get("name"));
			
			genresByArtist.add(genre);
		}

		if(genresByArtist.isEmpty()) {
			genresByArtist.add(new Genre());
		}
		return genresByArtist;
	}
	

	class GenreMapper implements RowMapper {
		
		@Override
		public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
			Genre genre = new Genre();
			genre.setName(rs.getString("name"));

			
			return genre;
		}
	}
	
public List<Genre> listGenres() {
		
		List<Genre> genreList = new ArrayList<>();
		
		List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from genre");
		
		for(Map<String, Object> map : maps) {
			Genre genre = new Genre();
			genre.setName((String) map.get("name"));
			genreList.add(genre);
		}

		return genreList;
	}
	
}

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

import com.sjsu.wusic.model.Artist;

@Repository
public class ArtistRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Artist findById(String id) { 
		return (Artist) jdbcTemplate.queryForObject("SELECT * FROM artist WHERE artist_id = ?", new Object[] {id}, new ArtistMapper());
	}
	
	class ArtistMapper implements RowMapper {

		@Override
		public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
			Artist artist = new Artist();
			artist.setId(rs.getString("artist_id"));
			artist.setName(rs.getString("name"));

			return artist;
		}
	}

	public List<Artist> discoverArtists() {
		List<Artist> randomArtists = new ArrayList<>();

		List<Map<String, Object>> maps =
				jdbcTemplate.queryForList("select * from artist order by rand() limit 100");
		for (Map<String, Object> map : maps) {

			Artist artist = new Artist();
			artist.setId((String) map.get("artist_id"));
			artist.setName((String) map.get("name"));

			randomArtists.add(artist);
		}

		return randomArtists;
	}
}

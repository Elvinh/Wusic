package com.sjsu.wusic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sjsu.wusic.model.Playlist;

@Repository
public class PlaylistRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Playlist findById(int id) {
		return (Playlist) jdbcTemplate.queryForObject("SELECT * FROM playlist WHERE playlist_id = ?", new Object[] {id}, new PlaylistMapper());
	}

	public void addSongToPlaylist(String songId, int playlistId){

		jdbcTemplate.update("INSERT INTO song_in_playlist VALUES (?, ?);", songId, playlistId);

		// increment song count
		int updatedSongCount = findById(playlistId).getSongCount() + 1;
		jdbcTemplate.update("UPDATE playlist SET song_count = ? WHERE playlist_id = ?", updatedSongCount, playlistId);
	}

	class PlaylistMapper implements RowMapper {
		
		@Override
		public Playlist mapRow(ResultSet rs, int rowNum) throws SQLException {
			Playlist playlist = new Playlist();
			playlist.setId(rs.getInt("playlist_id"));
			playlist.setName(rs.getString("name"));
			playlist.setSongCount(rs.getInt("song_count"));
			
			return playlist;
		}
	}
}
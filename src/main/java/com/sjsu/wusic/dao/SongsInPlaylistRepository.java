package com.sjsu.wusic.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sjsu.wusic.model.Song;

@Repository
public class SongsInPlaylistRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Song> findSongsInPlaylist(int playlistId) {
		
		List<Song> songsInPlaylist = new ArrayList<>();
		
		List<Map<String, Object>> maps =
				jdbcTemplate.queryForList("SELECT song.song_id, song.title, song.year, song.duration "
						+ "FROM song_in_playlist sip INNER JOIN song INNER JOIN playlist "
						+ "WHERE sip.song_id = song.song_id "
						+ "AND sip.playlist_id = playlist.playlist_id "
						+ "AND playlist.playlist_id = ?", playlistId);
		
        for (Map<String, Object> map : maps) {

            Song song = new Song();
            song.setId((String) map.get("song_id"));
            song.setTitle((String) map.get("title"));
            song.setYear((Integer) map.get("year"));
            song.setDuration((Float) map.get("duration"));

            songsInPlaylist.add(song);
        }

		return songsInPlaylist;
	}
}

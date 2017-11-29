package com.sjsu.wusic.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sjsu.wusic.model.Playlist;

@Repository
public class PlaylistsByUserRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Playlist> playlistsByUser(int userId) {
		
		List<Playlist> playlistsByUser = new ArrayList<>();
		
        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList("SELECT playlist.playlist_id, playlist.name, playlist.song_count "
                		+ "FROM user_owns_playlist INNER JOIN playlist INNER JOIN user "
                		+ "WHERE user_owns_playlist.playlist_id = playlist.playlist_id "
                		+ "AND user_owns_playlist.user_id = user.user_id AND user.user_id= ?", userId);

        for (Map<String, Object> map : maps) {

            Playlist playlist = new Playlist();
            playlist.setId((Integer) map.get("playlist_id"));
            playlist.setName((String) map.get("name"));
            playlist.setSongCount((Integer) map.get("song_count"));

            playlistsByUser.add(playlist);
        }
		
		return playlistsByUser;
	}

}

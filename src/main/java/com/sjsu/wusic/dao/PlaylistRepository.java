package com.sjsu.wusic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sjsu.wusic.model.PlaylistWithUser;
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
        return (Playlist) jdbcTemplate.queryForObject("SELECT * FROM playlist WHERE playlist_id = ?", new Object[]{id}, new PlaylistMapper());
    }

    public List<PlaylistWithUser> getAllPlaylists() {

        List<PlaylistWithUser> playlists = new ArrayList<>();

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT playlist.playlist_id, playlist.name " +
                "AS playlist_name, playlist.song_count, users.username, users.name FROM user_owns_playlist uop " +
                "INNER JOIN playlist INNER JOIN users WHERE uop.username = users.username " +
                "AND uop.playlist_id = playlist.playlist_id");

        for(Map<String, Object> map : maps) {
            PlaylistWithUser playlistWithUser = new PlaylistWithUser();
            playlistWithUser.setId((Integer) map.get("playlist_id"));
            playlistWithUser.setName((String) map.get("playlist_name"));
            playlistWithUser.setSongCount((Integer) map.get("song_count"));
            playlistWithUser.setUsername((String) map.get("username"));
            playlistWithUser.setUserDisplayName((String) map.get("name"));

            playlists.add(playlistWithUser);
        }

        return playlists;
    }

    public void addSongToPlaylist(String songId, int playlistId) {

        jdbcTemplate.update("INSERT INTO song_in_playlist VALUES (?, ?);", songId, playlistId);

        // increment song count
        int updatedSongCount = findById(playlistId).getSongCount() + 1;
        jdbcTemplate.update("UPDATE playlist SET song_count = ? WHERE playlist_id = ?", updatedSongCount, playlistId);
    }

    public void removeSongFromPlaylist(String songId, int playlistId) {

        jdbcTemplate.update("DELETE FROM song_in_playlist WHERE song_id = ? AND playlist_id = ?", songId, playlistId);

        // update song count
        int updatedSongCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM song_in_playlist WHERE playlist_id = ?", new Object[]{playlistId}, Integer.class);
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
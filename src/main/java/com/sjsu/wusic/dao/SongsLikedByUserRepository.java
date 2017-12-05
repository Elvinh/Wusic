package com.sjsu.wusic.dao;

import com.sjsu.wusic.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SongsLikedByUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Song> findByUserId(String username) {

        List<Song> songsLikedByUser = new ArrayList<>();

        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList("SELECT song.song_id, song.title, song.year FROM user_likes_song "
                		+ "INNER JOIN users INNER JOIN song WHERE user_likes_song.username = users.username "
                		+ "AND user_likes_song.song_id = song.song_id AND users.username = ?", username);

        for (Map<String, Object> map : maps) {

            Song song = new Song();
            song.setId((String) map.get("song_id"));
            song.setTitle((String) map.get("title"));
            song.setYear((Integer) map.get("year"));

            songsLikedByUser.add(song);
        }

        return songsLikedByUser;
    }
}

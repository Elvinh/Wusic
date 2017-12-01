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

    public List<Song> findByUserId(int userId) {

        List<Song> songsLikedByUser = new ArrayList<>();

        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList("SELECT song.song_id, song.title, song.year FROM user_likes_song "
                		+ "INNER JOIN user INNER JOIN song WHERE user_likes_song.user_id = user.user_id "
                		+ "AND user_likes_song.song_id = song.song_id AND user.user_id = ?", userId);

        for (Map<String, Object> map : maps) {

            Song song = new Song();
            song.setId((String) map.get("song_id"));
            song.setTitle((String) map.get("name"));
            song.setYear((Integer) map.get("year"));

            songsLikedByUser.add(song);

        }

        return songsLikedByUser;

    }
}

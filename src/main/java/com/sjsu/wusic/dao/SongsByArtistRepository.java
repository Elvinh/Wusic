package com.sjsu.wusic.dao;

import com.sjsu.wusic.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SongsByArtistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Song> findByArtistId(String artistId) {

        List<Song> songsByArtist = new ArrayList<>();

        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList("SELECT song.song_id, song.title, song.year FROM song "
                		+ "INNER JOIN artist INNER JOIN song_by_artist WHERE song_by_artist.song_id = song.song_id "
                		+ "AND song_by_artist.artist_id = artist.artist_id AND artist.artist_id = ?", artistId);

        for (Map<String, Object> map : maps) {

            Song song = new Song();
            song.setId((String) map.get("song_id"));
            song.setTitle((String) map.get("title"));
            song.setYear((Integer) map.get("year"));

            songsByArtist.add(song);

        }

        return songsByArtist;

    }
}

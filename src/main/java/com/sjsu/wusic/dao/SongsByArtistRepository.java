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

    public List<Song> findByArtistId(int artistId) {

        List<Song> songsByArtist = new ArrayList<>();

        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList("select song.song_id, song.name, song.year from artist_sings_song inner join song inner join artist " +
                        "where artist_sings_song.song_id = song.song_id and artist_sings_song.artist_id = artist.artist_id and artist.artist_id = ?", artistId);

        for (Map<String, Object> map : maps) {

            Song song = new Song();
            song.setId((Integer) map.get("song_id"));
            song.setName((String) map.get("name"));
            song.setYear((Integer) map.get("year"));

            songsByArtist.add(song);

        }

        return songsByArtist;

    }

}

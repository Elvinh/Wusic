package com.sjsu.wusic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sjsu.wusic.model.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sjsu.wusic.model.Album;

@Repository
public class AlbumRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Album findByName(String name) {
        return (Album) jdbcTemplate.queryForObject("select * from album where album.name = ?", new Object[]{name}, new AlbumMapper());
    }

    class AlbumMapper implements RowMapper {

        @Override
        public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
            Album album = new Album();
            album.setName(rs.getString("name"));
            album.setYear(rs.getInt("year"));

            return album;
        }
    }

    public List<Album> discoverAlbums() {
        List<Album> albumsDiscovered = new ArrayList<>();

        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList("select * from album order by rand() limit 100");

        for (Map<String, Object> map : maps) {

            Album album = new Album();
            album.setName((String) map.get("name"));
            album.setYear((Integer) map.get("year"));

            albumsDiscovered.add(album);
        }

        return albumsDiscovered;
    }


    public List<Album> findByArtistId(String artistId) {
        List<Album> albumsByArtist = new ArrayList<>();

        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList("SELECT al.name, al.year "
                        + "FROM artist_in_album aa INNER JOIN album al "
                        + "INNER JOIN artist ar "
                        + "WHERE ar.artist_id = aa.artist_id AND al.name = aa.album_name AND ar.artist_id = ?", artistId);

        for (Map<String, Object> map : maps) {

            Album album = new Album();
            album.setName((String) map.get("name"));
            album.setYear((Integer) map.get("year"));

            albumsByArtist.add(album);
        }

        return albumsByArtist;
    }

}

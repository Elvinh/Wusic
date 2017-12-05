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
import com.sjsu.wusic.model.Song;


@Repository
public class SongRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Song findById(String id) {
		return (Song) jdbcTemplate.queryForObject("select * from song where song_id = ?", new Object[]{id}, new SongMapper());
	}
	
	class SongMapper implements RowMapper {

		@Override
		public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
			Song song = new Song();
			song.setId(rs.getString("song_id"));
			song.setTitle(rs.getString("title"));
			song.setYear(rs.getInt("year"));
			return song;
		}
	}
	
	public List<Song> findAllSongs() {
		List<Song> allSongs = new ArrayList<>();
		List<Map<String, Object>> maps =
				jdbcTemplate.queryForList("select * from song order by rand() limit 100");

		for (Map<String, Object> map : maps) {
			Song song = new Song();
			song.setId((String) map.get("id"));
			song.setTitle((String) map.get("title"));
			song.setYear((Integer) map.get("year"));
			song.setDuration((Float) map.get("duration"));
			allSongs.add(song);
		}

		return allSongs;
	}
	
	public List<Song> findByAlbumName(String name) {
		List<Song> songsInAlbum = new ArrayList<>();
		List<Map<String, Object>> maps =
				jdbcTemplate.queryForList("select song.title, song.year, song.duration from song_in_album sa "
						+ "inner join song inner join album "
						+ "where song.song_id = sa.song_id AND album.name = sa.album_name "
						+ "AND album.name = ?", name);
		for (Map<String, Object> map : maps) {

			Song song = new Song();
			song.setId((String) map.get("id"));
			song.setTitle((String) map.get("title"));
			song.setYear((Integer) map.get("year"));
			song.setDuration((Float) map.get("duration"));
			songsInAlbum.add(song);
		}

		return songsInAlbum;
	}

	public Artist findArtistOfSong(String id) {
		return (Artist) jdbcTemplate.queryForObject("SELECT * from artist WHERE artist_id = (SELECT artist_id from song_by_artist WHERE song_id = ?)", new Object[]{id}, new GetArtistMapper());
	}

	class GetArtistMapper implements RowMapper {

		@Override
		public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
			Artist artist = new Artist();
			artist.setId(rs.getString("artist_id"));
			artist.setName(rs.getString("name"));
			return artist;
		}
	}

	public String findAlbumOfSong(String id) {
		return (String) jdbcTemplate.queryForObject("SELECT album_name from song_in_album WHERE song_id = ?", new Object[]{id}, new GetAlbumMapper());
	}

	class GetAlbumMapper implements RowMapper {

		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			String albumName = rs.getString("album_name");
			return albumName;
		}
	}
}

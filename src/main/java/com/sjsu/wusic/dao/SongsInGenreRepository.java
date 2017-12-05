package com.sjsu.wusic.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sjsu.wusic.model.Song;

@Repository
public class SongsInGenreRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<SongAndArtist> findSongsInGenre(String genreName) {
		List<SongAndArtist> songsInGenre = new ArrayList<>();

		List<Map<String, Object>> maps =
				jdbcTemplate.queryForList("SELECT song.song_id, song.title, song.year, song.duration, artist_from_genre.name FROM song " +
						"INNER JOIN (SELECT artist.name, artist.artist_id FROM artist " +
									"INNER JOIN artist_genre_tag " +
									"ON artist.artist_id = artist_genre_tag.artist_id AND artist_genre_tag.genre_name = ?) artist_from_genre " +
						"INNER JOIN song_by_artist " +
						"ON song_by_artist.artist_id = artist_from_genre.artist_id AND song.song_id = song_by_artist.song_id", genreName);

		for(Map<String, Object> map : maps) {
			Song song = new Song();
			String artistName = (String) map.get("name");
			song.setId((String) map.get("song_id"));
			song.setTitle((String) map.get("title"));
			song.setYear((Integer) map.get("year"));
			song.setDuration((Float) map.get("duration"));

			SongAndArtist songAndArtist = new SongAndArtist();
			songAndArtist.setSong(song);
			songAndArtist.setArtist(artistName);
			songsInGenre.add(songAndArtist);
		}

		return songsInGenre;
	}

	public class SongAndArtist {
	    private Song song;
	    private String artist;
		public Song getSong() {
			return song;
		}
		public void setSong(Song song) {
			this.song = song;
		}
		public String getArtist() {
			return artist;
		}
		public void setArtist(String artist) {
			this.artist = artist;
		}
	}
}

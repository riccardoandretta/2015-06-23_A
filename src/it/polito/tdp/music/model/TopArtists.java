package it.polito.tdp.music.model;

public class TopArtists {

	private int artistId;
	private String artist;
	private int listenings;

	public TopArtists(int artistId, String artist, int listenings) {
		super();
		this.artistId = artistId;
		this.artist = artist;
		this.listenings = listenings;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getListenings() {
		return listenings;
	}

	public void setListenings(int listenings) {
		this.listenings = listenings;
	}

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	@Override
	public String toString() {
		return String.format("%s, %d\n", artist, listenings); // (%-20s %d)
	}

}

package it.polito.tdp.music.model;

public class CountryPairFrequency {

	private int cnt;
	private int country1;
	private int country2;

	public CountryPairFrequency(int cnt, int country1, int country2) {
		super();
		this.cnt = cnt;
		this.country1 = country1;
		this.country2 = country2;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getCountry1() {
		return country1;
	}

	public void setCountry1(int country1) {
		this.country1 = country1;
	}

	public int getCountry2() {
		return country2;
	}

	public void setCountry2(int country2) {
		this.country2 = country2;
	}

	@Override
	public String toString() {
		return String.format("CountryPairFrequency [cnt=%s, country1=%s, country2=%s]", cnt, country1, country2);
	}

}

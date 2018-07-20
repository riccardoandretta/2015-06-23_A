package it.polito.tdp.music.model;

import java.time.Month;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Model m = new Model();
		List<Month> mesi = m.getMonths();
		
		for(TopArtists ta : m.trovaArtisti(mesi.get(0))) {
			System.out.print(ta);
		}
		
//		int sum = 0;
//		for(TopArtists ta : m.trovaArtisti(mesi.get(0))) {
//			sum += ta.getListenings();
//		}
//		System.out.print(sum);
		
		
		m.creaGrafo(mesi.get(0));
		
		System.out.print(m.maxDistanzaCountry());
		
	}

}

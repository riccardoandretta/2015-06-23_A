package it.polito.tdp.music.model;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.music.db.MusicDAO;

public class Model {

	private MusicDAO mdao;
	private List<TopArtists> topArtists;
	private Graph<Country, DefaultWeightedEdge> grafo;

	public Model() {
		mdao = new MusicDAO();
	}

	public List<Month> getMonths() {
		List<Month> mesi = new ArrayList<>();

		for (int i = 1; i <= 12; i++) {
			mesi.add(Month.of(i));
		}
		return mesi;
	}

	public List<TopArtists> trovaArtisti(Month month) {
		topArtists = mdao.getMostListenedArtistFromMonth(month);
		return topArtists;
	}

	public void creaGrafo(Month mese) {

		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		Set<Country> countries = new HashSet<>();

		for (TopArtists a : topArtists) {
			countries.addAll(mdao.getCountryByTopArtist(a, mese));
		}

		Graphs.addAllVertices(grafo, countries);
		// System.out.print(grafo.vertexSet().size());

		List<CountryPairFrequency> countryPairs = mdao.getArtistsForCountryPairs(mese);

		for (Country c1 : grafo.vertexSet()) {
			for (Country c2 : grafo.vertexSet()) {
				if (c1.getId() < c2.getId()) {

					int peso = calcolaPeso(c1, c2, countryPairs);

					if (peso != 0) {
						Graphs.addEdge(grafo, c1, c2, peso);
					}
				}
			}
		}
//		System.out.print(grafo.edgeSet().size());
	}

	private int calcolaPeso(Country c1, Country c2, List<CountryPairFrequency> countryPairs) {
		
		for (CountryPairFrequency cpf : countryPairs) {
			if (c1.getId() == cpf.getCountry1() && c2.getId() == cpf.getCountry2())
				return cpf.getCnt();
		}

		return 0;
	}
	
	
	public int maxDistanzaCountry() {
		int max = 0 ;
		for( DefaultWeightedEdge e : grafo.edgeSet() ) {
			
			if ( grafo.getEdgeWeight(e) > max )
				
				max = (int)grafo.getEdgeWeight(e) ;
		}
		return max ;
}
}

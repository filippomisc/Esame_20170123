package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private List<Country> countries;
	private SimpleDirectedGraph<Country, DefaultEdge> graph ;
	private BordersDAO dao;
	
	


	public Model() {

		dao = new BordersDAO();
		countries = dao.loadAllCountries();
		graph = new SimpleDirectedGraph<>(DefaultEdge.class);
	}

	public void createGraph(int anno) {
		
		Graphs.addAllVertices(graph, countries);
		System.out.println("vertici creati: " + graph.vertexSet().size());
		
		List<CountryPair> confinanti = new ArrayList<CountryPair>(dao.getCountryPairs(anno));
		
		for (CountryPair countryPair : confinanti) {
			
			
			Country c1 = new Country(countryPair.getC1().getcCode(), countryPair.getC1().getStateAbb(), countryPair.getC1().getStateName());
			

			Country c2 = new Country(countryPair.getC2().getcCode(), countryPair.getC2().getStateAbb(), countryPair.getC2().getStateName());
			
			graph.addEdge(c1, c2);

		}
		
		System.out.println("archi creati: " + graph.edgeSet().size());
	}
	
	
	public List<CountryCount> countryCounts(){
		
		ArrayList<CountryCount> cCounts = new ArrayList<CountryCount>();
		
		for (Country c : graph.vertexSet()) {
			
			CountryCount countryCount = new CountryCount(c, graph.inDegreeOf(c));
			
			cCounts.add(countryCount);
			
		}
		
		Collections.sort(cCounts);
		
		
		return cCounts;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public List<CountryCount> getCountryCounts() {
//		List<CountryCount> stats = new ArrayList<>() ;
//		
//		for(Country c: graph.vertexSet()) {
//			stats.add( new CountryCount(c, graph.degreeOf(c))) ;
//		}
//		
//		Collections.sort(stats);
//		
//		return stats ;
//	}
	
	
	
	public List<Country> getCountries() {
		return countries;
	}

//	// package-level visibility (only needed by simulator)
//	SimpleGraph<Country, DefaultEdge> getGraph() {
//		return graph;
//	}

	public List<CountryCount> simulaImmigrazione(Country iniziale) {

		return null;
	}


}

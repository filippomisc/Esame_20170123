package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private List<Country> countries ;
	

	private SimpleGraph<Country, DefaultEdge> graph ;
	
	

	public void createGraph(int anno) {
		
		BordersDAO dao = new BordersDAO() ;
		
		List<CountryPair> cplist = dao.getCountryPairs(anno) ;
		
		if(cplist==null || cplist.isEmpty())
			throw new RuntimeException("No country pairs for specified year") ;
		
		graph = new SimpleGraph<>(DefaultEdge.class) ;
		
		for(CountryPair cp : cplist) {
			graph.addVertex(cp.getC1()) ; // se c'è già, il Set lo rifiuta
			graph.addVertex(cp.getC2()) ;
			
			graph.addEdge(cp.getC1(), cp.getC2()) ;
		}
		
		countries = new ArrayList<>( graph.vertexSet() ) ;
		Collections.sort(countries);
		
	}
	
	public List<CountryCount> getCountryCounts() {
		List<CountryCount> stats = new ArrayList<>() ;
		
		for(Country c: graph.vertexSet()) {
			stats.add( new CountryCount(c, graph.degreeOf(c))) ;
		}
		
		Collections.sort(stats);
		
		return stats ;
	}
	
	public static void main(String[] args) {
		Model m = new Model() ;
		
		m.createGraph(1900);
		
		System.out.format("%d vertices, %d edges\n", m.graph.vertexSet().size(),
				m.graph.edgeSet().size());
		
		List<CountryCount> stats = m.getCountryCounts() ;
		System.out.println(stats);
	}

	
	public List<Country> getCountries() {
		return countries;
	}

	// package-level visibility (only needed by simulator)
	SimpleGraph<Country, DefaultEdge> getGraph() {
		return graph;
	}

	public List<CountryCount> simulaImmigrazione(Country iniziale) {
		Simula simula = new Simula(this, iniziale) ;
		simula.run(); 
		
		return null;
	}


}

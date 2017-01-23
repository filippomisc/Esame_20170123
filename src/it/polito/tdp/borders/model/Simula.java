package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;

public class Simula {

	private final int PERSONS = 1000; // numero di persone iniziali
	private final int STANTIAL = 50; // percentuale che diventa stanziale

	// coda di simulazione
	private PriorityQueue<SimEvent> queue;

	// stato del sistema
	private Model model;
	private Map<Country, Integer> occupation = new HashMap<>(); // numero di
																// stanziali in
																// ciascuno
																// stato
	private int maxTime = 0;

	public Simula(Model model, Country initial) {

		this.model = model;
		this.queue = new PriorityQueue<>();

		// inizialmente stati vuoti
		for (Country c : model.getCountries())
			occupation.put(c, 0);

		// evento iniziale: prima immigrazione
		queue.add(new SimEvent(1, PERSONS, initial));

	}

	public void run() {

		while (!queue.isEmpty()) {
			SimEvent e = queue.poll();

			Country qui = e.getDestination();

			if (e.getTime() > this.maxTime)
				this.maxTime = e.getTime();

			// ho ricevuto delle persone in uno stato
			int persone = e.getNumPeople();

			System.out.format("%d persone in %s\n", persone, qui);

			// calcola quelli che si sposteranno
			int spostano = persone - (persone * STANTIAL / 100);

			// calcola quanti stati vicini
			int stati = model.getGraph().degreeOf(qui);

			// calcola quanti vanno in ciascuno stato
			int perStato = spostano / stati;

			// incrementa gli stanziali: (persone - perStato*stati)
			occupation.put(qui, occupation.get(qui) + (persone - perStato * stati));

			if (perStato != 0) {
				for (Country vicina : Graphs.neighborListOf(model.getGraph(), qui)) {

					SimEvent e2 = new SimEvent(e.getTime() + 1, perStato, vicina);
					queue.add(e2);

				}
			}

		}

	}

	public int getMaxTime() {
		return maxTime;
	}
	
	public List<CountryCount> getOccupationList() {
		List<CountryCount> stats = new ArrayList<>() ;
		for(Country c: occupation.keySet()) {
			stats.add(new CountryCount(c, occupation.get(c))) ;
		}
		
		Collections.sort(stats);
		
		return stats ;
	}
	

}

package it.polito.tdp.borders.model;

public class TestModel {
	public static void main(String[] args) {

	
	Model m = new Model();
	
	m.createGraph(1993);
	
	System.out.println(m.countryCounts().toString());
	
	}
	
}

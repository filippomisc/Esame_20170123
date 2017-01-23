package it.polito.tdp.borders.model;

public class CountryCount implements Comparable<CountryCount> {
	
	private Country country ;
	private int num ;
	
	
	
	
	public CountryCount(Country country, int num) {
		super();
		this.country = country;
		this.num = num;
	}




	public Country getCountry() {
		return country;
	}




	public void setCountry(Country country) {
		this.country = country;
	}




	public int getNum() {
		return num;
	}




	public void setNum(int num) {
		this.num = num;
	}
	
	




	@Override
	public String toString() {
		return "[" + country + "=" + num + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryCount other = (CountryCount) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}




	@Override
	public int compareTo(CountryCount other) {
		return -( this.num - other.num ) ;
	}
	
	

}

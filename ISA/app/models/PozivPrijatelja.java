package models;

import static javax.persistence.GenerationType.IDENTITY;





import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="PozivPrijatelja")

public class PozivPrijatelja extends Model {

	
	
	@Column(name = "Potvrdio", unique = false, nullable = false)
	public Boolean potvrdio;
	
	@Column(name = "Ocena", unique = false, nullable = false)
	public Double ocena;
	
	@ManyToOne
	public Korisnik korisnik;
	
	@ManyToOne
	public Rezervacija rezervacija;

	public PozivPrijatelja(Boolean potvrdio, Double ocena, Korisnik korisnik,
			Rezervacija rezervacija) {
		super();
		this.potvrdio = potvrdio;
		this.ocena = ocena;
		this.korisnik = korisnik;
		this.rezervacija = rezervacija;
	}

	
	
}

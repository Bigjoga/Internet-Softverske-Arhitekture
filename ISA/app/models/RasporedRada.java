package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="Raspored_rada")
public class RasporedRada extends Model{

	@Column(name = "Datum", unique = false, nullable = false)
	public Date datum;
	
	@Column(name = "Smena", unique = false, nullable = false)
	public String smena;
	
	@ManyToOne
	public Korisnik korisnik;

	public RasporedRada(Date datum, String smena, Korisnik korisnik) {
		super();
		this.datum = datum;
		this.smena = smena;
		this.korisnik = korisnik;
	}

	public RasporedRada() {
		super();
	}
	
	
	
}

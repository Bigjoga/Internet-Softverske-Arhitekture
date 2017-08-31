package models;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="Rezervacija")

public class Rezervacija extends Model {

	
	

	@Column(name = "DatumVreme", unique = false, nullable = false)
	public Date datumVreme;

	@Column(name = "Trajanje", unique = false, nullable = false)
	public Integer trajanje;
	
	@ManyToOne
	public Korisnik korisnik;
	
	@ManyToOne
	public Restoran restoran;
	
	@OneToMany(mappedBy = "rezervacija")
	public List<PozivPrijatelja> pozvaniPrijateljiInfo;

	public Rezervacija(Date datumVreme, Integer trajanje, Korisnik korisnik,
			Restoran restoran, List<PozivPrijatelja> pozvaniPrijateljiInfo) {
		super();
		this.datumVreme = datumVreme;
		this.trajanje = trajanje;
		this.korisnik = korisnik;
		this.restoran = restoran;
		this.pozvaniPrijateljiInfo = pozvaniPrijateljiInfo;
	}

	

	
	
	
}

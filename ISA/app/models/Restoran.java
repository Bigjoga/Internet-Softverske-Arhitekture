package models;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="Restoran")
public class Restoran extends Model {

	
	
	@Column(name = "Naziv_restorana", unique = false, nullable = false)
	public String nazivRestorana;
	
	@Column(name = "Opis_restorana", unique = false, nullable = true)
	public String opisRestorana;
	
	@Column(name = "DimX_restorana", unique = false, nullable = false)
	public Integer dimX;
	
	@Column(name = "DimY_restorana", unique = false, nullable = false)
	public Integer dimY;
	
	@Column(name = "ProsecnaOcena", unique = false, nullable = false)
	public Double prosecnaOcena;
	
	@Column(name = "UkupanBrojOcena", unique = false, nullable = false)
	public Integer ukupanBrojOcena;

	@Column(name = "Adresa_restorana", unique = false, nullable = false)
	public String adresa;
	
	/*@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "restoran")
	private Set<Korisnik> menadzeri = new HashSet<Korisnik>();*/
	@OneToMany(mappedBy = "restoran")
	public List<Korisnik> menadzeri ;

	public Restoran(String nazivRestorana, String opisRestorana, Integer dimX,
			Integer dimY, Double prosecnaOcena, Integer ukupanBrojOcena,
			String adresa, List<Korisnik> menadzeri) {
		super();
		this.nazivRestorana = nazivRestorana;
		this.opisRestorana = opisRestorana;
		this.dimX = dimX;
		this.dimY = dimY;
		this.prosecnaOcena = prosecnaOcena;
		this.ukupanBrojOcena = ukupanBrojOcena;
		this.adresa = adresa;
		this.menadzeri = menadzeri;
	}
	
	
	
	
	
}

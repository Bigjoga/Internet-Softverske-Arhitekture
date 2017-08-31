package models;

import static javax.persistence.CascadeType.ALL;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name = "Korisnik")

public class Korisnik extends Model {

	
	
	@Column(name = "Email_korisnika", unique = true, nullable = false)
	public String email;

	@Column(name = "Sifra_korisnika", unique = false, nullable = false)
	public String sifra;
	
	@Column(name = "Ime_korisnika", unique = false, nullable = false)
	public String ime;
	
	@Column(name = "Adresa_korisnika", unique = false, nullable = true)
	public String adresa;
	
	//*********KUMULATIVAN ZBIR PRAVIH POSETA
	@Column(name = "Broj_poseta", unique = false, nullable = false)
	public Integer brojPoseta;
	//***************************************
	
	@ManyToOne
	public UlogaKorisnika uloga;
	
	@ManyToOne
	public Restoran restoran;
	
	@OneToMany(mappedBy = "korisnik")
	public List<Rezervacija> rezervacije ;

	public Korisnik(String email, String sifra, String ime, String adresa,
			Integer brojPoseta, UlogaKorisnika uloga, Restoran restoran,
			List<Rezervacija> rezervacije) {
		super();
		this.email = email;
		this.sifra = sifra;
		this.ime = ime;
		this.adresa = adresa;
		this.brojPoseta = brojPoseta;
		this.uloga = uloga;
		this.restoran = restoran;
		this.rezervacije = rezervacije;
	}
	
	public Korisnik(String email, String sifra, String ime, String adresa,
			Integer brojPoseta, UlogaKorisnika uloga)  {
		super();
		this.email = email;
		this.sifra = sifra;
		this.ime = ime;
		this.adresa = adresa;
		this.brojPoseta = brojPoseta;
		this.uloga = uloga;
		
	}
	
}

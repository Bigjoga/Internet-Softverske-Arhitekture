package models;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="OcenaPrijatelja")
public class OcenaPrijatelja extends Model {

	
	@Column(name = "Prosecna_ocena_prijatelja", unique = false, nullable = false)
	public Double prosecnaOcenaPrijatelja;
	
	@ManyToOne
	
	public Korisnik korisnik;

	@ManyToOne
	public Restoran restoran;

	public OcenaPrijatelja(Double prosecnaOcenaPrijatelja, Korisnik korisnik,
			Restoran restoran) {
		super();
		this.prosecnaOcenaPrijatelja = prosecnaOcenaPrijatelja;
		this.korisnik = korisnik;
		this.restoran = restoran;
	}

	
	
}

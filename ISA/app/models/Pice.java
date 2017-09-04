package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="Pice")
public class Pice extends Model {

	@Column(name = "Naziv_pica", unique = false, nullable = false)
	public String nazivPica;
	
	@Column(name = "Opis_pica", unique = false, nullable = false)
	public String opisPica;
	
	@Column(name = "Cena", unique = false, nullable = false)
	public Double cena;
	
	@ManyToOne
	public KartaPica kartaPica;

}

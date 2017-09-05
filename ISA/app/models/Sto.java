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
@Table(name="Sto")

public class Sto extends Model {

	@Column(name = "Naziv_stola", unique = true, nullable = false)
	public String nazivStola;
	
	@Column(name = "Broj_mesta", unique = false, nullable = false)
	public Integer brojMesta;
	
	@ManyToOne
	public Restoran restoran;
	
	@OneToMany(mappedBy = "sto")
	public List<Rezervacija> rezervacija;

	public Sto(String nazivStola, Integer brojMesta, Restoran restoran) {
		super();
		this.nazivStola = nazivStola;
		this.brojMesta = brojMesta;
		this.restoran = restoran;
	}	
}

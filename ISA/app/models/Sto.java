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

	

	@Column(name = "Naziv_stola", unique = false, nullable = false)
	public String nazivStola;
	
	@Column(name = "PozX_stola", unique = false, nullable = false)
	public Integer pozX;

	@Column(name = "PozY_stola", unique = false, nullable = false)
	public Integer pozY;
	
	@Column(name = "Broj_mesta", unique = false, nullable = false)
	public Integer brojMesta;
	
	@ManyToOne
	public Restoran restoran;
		
	@OneToMany(mappedBy = "sto")
	public List<RezervisaniStolovi> rezervisaniStolovi ;

	public Sto(String nazivStola, Integer pozX, Integer pozY,
			Integer brojMesta, Restoran restoran,
			List<RezervisaniStolovi> rezervisaniStolovi) {
		super();
		this.nazivStola = nazivStola;
		this.pozX = pozX;
		this.pozY = pozY;
		this.brojMesta = brojMesta;
		this.restoran = restoran;
		this.rezervisaniStolovi = rezervisaniStolovi;
	}

	
	
}

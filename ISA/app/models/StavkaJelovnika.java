package models;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

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
@Table(name="StavkaJelovnika")

public class StavkaJelovnika extends Model {

	
	

	@Column(name = "Cena_stavke_jelovnika", unique = false, nullable = false)
	public Double cena;
	
	@ManyToOne
	public Jelovnik jelovnik;
	
	@ManyToOne
	public Jelo jelo;
	
	@OneToMany(mappedBy="stavkaJlovnika")
	public List<Porudzbina> porudzbina;

	public StavkaJelovnika(Double cena, Jelovnik jelovnik, Jelo jelo) {
		super();
		this.cena = cena;
		this.jelovnik = jelovnik;
		this.jelo = jelo;
	}

	


	
	
}

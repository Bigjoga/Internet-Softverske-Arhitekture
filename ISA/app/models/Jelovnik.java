package models;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
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
@Table(name="Jelovnik")
public class Jelovnik extends Model {

	
	
	public Jelovnik(String nazivJelovnika, Restoran restoran,
			List<StavkaJelovnika> stavkeJelovnika) {
		super();
		this.nazivJelovnika = nazivJelovnika;
		this.restoran = restoran;
		this.stavkeJelovnika = stavkeJelovnika;
	}

	@Column(name = "Naziv_jelovnika", unique = false, nullable = false)
	public String nazivJelovnika;
	
	@ManyToOne
	public Restoran restoran;
	
	@OneToMany(mappedBy = "jelovnik")
	public List<StavkaJelovnika> stavkeJelovnika ;

	public Jelovnik() {
		super();
	}
}

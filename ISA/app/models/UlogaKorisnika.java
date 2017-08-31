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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="UlogaKorisnika")

public class UlogaKorisnika extends Model{

	@Column(name = "Naziv_uloge", unique = false, nullable = false)
	public String nazivUloge;
	
	@OneToMany(mappedBy = "uloga")
	public List<Korisnik> korisnici ;

	public UlogaKorisnika(String nazivUloge, List<Korisnik> korisnici) {
		super();
		this.nazivUloge = nazivUloge;
		this.korisnici = korisnici;
	}

}

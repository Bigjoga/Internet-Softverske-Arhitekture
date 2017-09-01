package models;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.db.jpa.Model;

@Entity
@Table(name="Prijatelji")

public class Prijatelj extends Model {

	@ManyToOne
	public Korisnik idKor1;
	
	@ManyToOne()
	public Korisnik idKor2;

	public Prijatelj(Korisnik idKor1, Korisnik idKor2) {
		super();
		this.idKor1 = idKor1;
		this.idKor2 = idKor2;
	}

	
	
	
}

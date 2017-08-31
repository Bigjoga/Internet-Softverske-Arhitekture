package models;


import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="Jelo")
public  class Jelo extends Model{

	
	
	

	public Jelo(String nazivJela, String opisJela,
			List<StavkaJelovnika> stavkeJelovnika) {
		super();
		this.nazivJela = nazivJela;
		this.opisJela = opisJela;
		this.stavkeJelovnika = stavkeJelovnika;
	}

	@Column(name = "Naziv_jela", unique = false, nullable = false)
	public String nazivJela;
	
	@Column(name = "Opis_jela", unique = false, nullable = false)
	public String opisJela;
	
	@OneToMany(mappedBy = "jelo")
	public List<StavkaJelovnika> stavkeJelovnika ;

	
	
	
	
}

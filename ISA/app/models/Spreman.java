package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="Spremna_jela")
public class Spreman extends Model{

	@ManyToOne
	public StavkaJelovnika stavkaJelovnika;
	
	@ManyToOne
	public Pice pice;

	public Spreman(StavkaJelovnika stavkaJelovnika) {
		super();
		this.stavkaJelovnika = stavkaJelovnika;
	}

	public Spreman(Pice pice) {
		super();
		this.pice = pice;
	}
}

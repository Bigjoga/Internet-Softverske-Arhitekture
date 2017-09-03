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

	public Spreman(StavkaJelovnika stavkaJelovnika) {
		super();
		this.stavkaJelovnika = stavkaJelovnika;
	}
}

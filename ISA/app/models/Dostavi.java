package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="Dostavi")
public class Dostavi extends Model{

	@ManyToOne
	public StavkaJelovnika stavkaJelovnika;

	public Dostavi(StavkaJelovnika stavkaJelovnika) {
		super();
		this.stavkaJelovnika = stavkaJelovnika;
	}
	
	
}
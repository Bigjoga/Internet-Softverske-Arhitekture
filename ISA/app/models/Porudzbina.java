package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="Porudzbina")
public class Porudzbina extends Model{
	
	@ManyToOne
	public StavkaJelovnika stavkaJlovnika;

	@ManyToOne
	public Restoran restoran;

	public Porudzbina(StavkaJelovnika stavkaJlovnika, Restoran restoran) {
		super();
		this.stavkaJlovnika = stavkaJlovnika;
		this.restoran = restoran;
	}

}
package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="Porudzbina_pica")
public class PorudzbinaPica extends Model{
	
	@ManyToOne
	public Pice pice;

	@ManyToOne
	public Restoran restoran;

	public PorudzbinaPica(Pice pice, Restoran restoran) {
		super();
		this.pice = pice;
		this.restoran = restoran;
	}
}
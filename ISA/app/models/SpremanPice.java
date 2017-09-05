package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="Spremna_pica")
public class SpremanPice extends Model{
	
	@ManyToOne
	public Pice pice;

	public SpremanPice(Pice pice) {
		super();
		this.pice = pice;
	}
}

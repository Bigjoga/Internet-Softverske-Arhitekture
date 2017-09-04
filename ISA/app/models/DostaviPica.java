package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="Dostavi_pica")
public class DostaviPica extends Model{

	@ManyToOne
	public Pice pice;

	public DostaviPica(Pice pice) {
		super();
		this.pice = pice;
	}

}
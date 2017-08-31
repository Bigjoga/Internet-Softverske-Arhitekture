package models;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="RezervisaniStolovi")
public class RezervisaniStolovi extends Model {

	
	
	
	@ManyToOne
	public Rezervacija rezervacija;

	@ManyToOne
	public Sto sto;

	public RezervisaniStolovi(Rezervacija rezervacija, Sto sto) {
		super();
		this.rezervacija = rezervacija;
		this.sto = sto;
	}

	
	
}

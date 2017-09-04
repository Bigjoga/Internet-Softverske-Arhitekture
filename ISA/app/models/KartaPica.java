package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="Karta_pica")
public class KartaPica extends Model{

	@Column(name = "Naziv_karte_pica", unique = false, nullable = false)
	public String nazivKartePica;
	
	@ManyToOne
	public Restoran restoran;
	
	@OneToMany(mappedBy = "kartaPica")
	public List<Pice> pica;
	
}
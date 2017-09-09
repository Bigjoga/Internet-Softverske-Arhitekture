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
@Table(name="Ponuda")
public class Ponuda extends Model{

	@Column(name = "Kolicina", unique = false, nullable = false)
	public String kolicina;
	
	@Column(name = "Cena", unique = false, nullable = false)
	public String cena;
	
	@Column(name = "RokPonude", unique = false, nullable = false)
	public String rokPonude;
	
	@Column(name = "StavkaPonude", unique = false, nullable = false)
	public String stavkaPonude;
	
	@Column(name = "Prihvaceno", unique = false, nullable = false)
	public String prihvaceno;

	@ManyToOne
	public Restoran restoran;

	public Ponuda(String stavkaPonude, Restoran restoran, String prihvaceno, String kolicina, String cena, String rokPonude) {
		super();
		this.stavkaPonude = stavkaPonude;
		this.restoran = restoran;
		this.prihvaceno = prihvaceno;
		this.kolicina = kolicina;
		this.cena = cena;
		this.rokPonude = rokPonude;
	}

}
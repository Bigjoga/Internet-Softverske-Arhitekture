package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Korisnik;
import models.RasporedRada;
import models.UlogaKorisnika;
import play.mvc.Controller;

public class RasporediRada extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty()){
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<RasporedRada> rasporedRada = RasporedRada.findAll();
		List<Korisnik> korisnici = Korisnik.findAll();
		List<RasporedRada> rasporedZaPrikaz= new ArrayList<>();
		
		Korisnik korisnik= new Korisnik();
		
		for(int i=0; i<korisnici.size(); i++)
		{
			if(korisnici.get(i).email.equals(session.get("email")))
			{
				korisnik=korisnici.get(i);
			}
		}
		
		for(int i=0 ; i<rasporedRada.size();i++){
			if(rasporedRada.get(i).korisnik.email.toString().equals(korisnik.email.toString()))
			{
				rasporedZaPrikaz.add(rasporedRada.get(i));
			}
		}
		
		render(rasporedZaPrikaz,mode,selectedIndex);
	}
	
	public static void showMenadzer(String mode, Long selectedIndex)
	{
		
		if(session.isEmpty()){
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<RasporedRada> rasporedRada = RasporedRada.findAll();
		List<RasporedRada> rasporedZaPrikaz= new ArrayList<>();
		
		//List<Korisnik> korisnici = Korisnik.findAll();
		
		Long id= Long.parseLong(session.get("zaposleni"));
		Korisnik korisnik= Korisnik.findById(id);
		
		List<Korisnik> listaKorisnikaZaPrikaz = new ArrayList<>();
		listaKorisnikaZaPrikaz.add(korisnik);
		
		
		for(int i=0 ; i<rasporedRada.size();i++){
			if(rasporedRada.get(i).korisnik.email.toString().equals(korisnik.email.toString()))
			{
				rasporedZaPrikaz.add(rasporedRada.get(i));
			}
		}
		
		render(listaKorisnikaZaPrikaz,rasporedZaPrikaz,mode,selectedIndex);
	}
		
	public static void create(RasporedRada rasporedRada, Long korisnik)
	{
		Korisnik kor = Korisnik.findById(korisnik);
		RasporedRada rsp = new RasporedRada(rasporedRada.datum, rasporedRada.smena, kor);
		rsp.save();
		showMenadzer("add", rsp.id);
	}
	
	public static void edit(RasporedRada rasporedRada, Long korisnik)
	{
		Korisnik kor = Korisnik.findById(korisnik);
		rasporedRada.korisnik = kor;
		rasporedRada.save();
		showMenadzer("edit", rasporedRada.id);
	}
	
	public static void delete(Long id)
	{
		RasporedRada rsp = RasporedRada.findById(id);
		rsp.delete();
		showMenadzer("edit", rsp.id-1);
	}
	
	
	
	
}

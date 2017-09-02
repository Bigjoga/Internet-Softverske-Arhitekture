package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Korisnik;
import models.Restoran;
import models.Rezervacija;
import play.mvc.Controller;

public class Rezervacije extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Restoran> restorani = Restoran.findAll();
		List<Korisnik> korisnici = Korisnik.findAll();
		List<Rezervacija> rezervacije = Rezervacija.findAll();
		List<Rezervacija> listaRezervacijaZaPrikaz = new ArrayList<>();
		
		for(int i=0; i<rezervacije.size(); i++)
		{
			if(rezervacije.get(i).korisnik.email.equals(session.get("email")))
			{
				listaRezervacijaZaPrikaz.add(rezervacije.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(restorani,korisnici,listaRezervacijaZaPrikaz,mode,selectedIndex);
	}
	
	public static void delete(Long id)
	{
		Rezervacija rez = Rezervacija.findById(id);
		rez.delete();
		show("edit", rez.id-1);
	}
}

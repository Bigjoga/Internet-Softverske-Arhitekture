package controllers;

import java.util.ArrayList;
import java.util.List;

import com.sun.prism.RenderTarget;

import models.Korisnik;
import models.Restoran;
import models.UlogaKorisnika;
import play.mvc.Controller;

public class Korisnici extends Controller{

	
	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty()){
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Korisnik> korisnici = Korisnik.findAll();
		List<Korisnik> listaKorisnikaZaPrikaz = new ArrayList<Korisnik>();
		List<Restoran> restorani = Restoran.findAll();
		List<UlogaKorisnika> uloge = UlogaKorisnika.findAll();
		
		for(int i=0; i<korisnici.size(); i++)
		{
			if(korisnici.get(i).email.equals(session.get("email")))
			{
				listaKorisnikaZaPrikaz.add(korisnici.get(i));
			}
		}
		
		System.out.println(session.get("email"));
		System.out.println(korisnici.get(0).uloga.toString());
		
		render(restorani,uloge,listaKorisnikaZaPrikaz,mode,selectedIndex);	
	}
	
	public static void edit(Korisnik korisnik, Long restoran)
	{
		Restoran rest = Restoran.findById(restoran);
		korisnik.restoran = rest;
		korisnik.save();
		show("edit",korisnik.id);
	}
	
	public static void filter(Korisnik korisnik)
	{
		List<Korisnik> korisnici = Korisnik.find("byImeLikeAndEmailLike", "%"+korisnik.ime+"%", "%"+korisnik.email+"%").fetch();                      
		String mode = "edit";
		renderTemplate("Korisnici/show.html", korisnici,mode);
	}
}

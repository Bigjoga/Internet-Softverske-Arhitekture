package controllers;

import java.util.List;

import com.sun.prism.RenderTarget;

import models.Korisnik;
import models.Restoran;
import models.UlogaKorisnika;
import play.mvc.Controller;

public class Korisnici extends Controller{

	
	public static void show(String mode, Long selectedIndex)
	{
		List<Korisnik> korisnici = Korisnik.findAll();
		List<Restoran> restorani = Restoran.findAll();
		List<UlogaKorisnika> uloge = UlogaKorisnika.findAll();
		
		System.out.println(session.get("email"));
		System.out.println(korisnici.get(0).uloga.toString());
		
		render(restorani,uloge,korisnici,mode,selectedIndex);	
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

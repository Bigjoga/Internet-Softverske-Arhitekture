package controllers;

import java.util.List;

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
}

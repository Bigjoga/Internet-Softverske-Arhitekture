package controllers;

import models.Korisnik;
import play.mvc.Controller;

public class Registracija extends Controller{

	public static void register(Korisnik korisnik, Long Restoran, Long UlogaKorisnika)
	{
		validation.required(korisnik.adresa);
		validation.required(korisnik.email);
		validation.required(korisnik.ime);
		validation.required(korisnik.sifra);
		validation.maxSize(korisnik.adresa, 50);
		validation.maxSize(korisnik.email, 50);
		validation.maxSize(korisnik.ime, 50);
		validation.maxSize(korisnik.sifra, 50);
		
	}
	
}

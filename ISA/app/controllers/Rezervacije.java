package controllers;

import java.util.List;

import models.Korisnik;
import models.Restoran;
import models.Rezervacija;
import play.mvc.Controller;

public class Rezervacije extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		List<Restoran> restorani = Restoran.findAll();
		List<Korisnik> korisnici = Korisnik.findAll();
		List<Rezervacija> rezervacije = Rezervacija.findAll();
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(restorani,korisnici,rezervacije,mode,selectedIndex);
	}
	
}

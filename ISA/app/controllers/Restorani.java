package controllers;

import java.util.List;

import models.Korisnik;
import models.Restoran;
import models.UlogaKorisnika;
import play.mvc.Controller;

public class Restorani extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		List<Restoran> restorani = Restoran.findAll();
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(restorani,mode,selectedIndex);
	}
	
}

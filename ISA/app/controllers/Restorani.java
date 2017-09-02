package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Korisnik;
import models.Restoran;
import models.UlogaKorisnika;
import play.mvc.Controller;

public class Restorani extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Restoran> restorani = Restoran.findAll();
	
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(restorani,mode,selectedIndex);
	}
	
	public static void edit(Restoran restoran)
	{ 
		restoran.save();
		show("add", restoran.id);
	}
}

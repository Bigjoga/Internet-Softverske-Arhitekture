package controllers;

import java.util.ArrayList;
import java.util.List;

import models.KartaPica;
import models.Pice;
import models.PorudzbinaPica;
import models.Restoran;
import models.StavkaJelovnika;
import play.mvc.Controller;

public class Pica extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		System.out.println("IZABRANA KARTA PICA JE -----> " + session.get("kartaPica"));
		List<Pice> pica = Pice.findAll();
		List<Pice> picaZaPrikaz = new ArrayList<>();
		
		for(int i=0; i<pica.size(); i++)
		{
			if(pica.get(i).kartaPica.nazivKartePica.equals(session.get("kartaPica")))
			{
				picaZaPrikaz.add(pica.get(i));
			}
		}
		
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(picaZaPrikaz, pica, mode, selectedIndex);
	}
	
	public static void naruci(Pice pice)
	{ 
		session.put("pice", pice.cena);
		List<Restoran> restorani = Restoran.findAll();
		Restoran restoran = new Restoran();
		
		for(int i = 0; i<restorani.size(); i++)
		{
			if(restorani.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				restoran = restorani.get(i);
			}
		}
		
		PorudzbinaPica porudzbinaPica = new PorudzbinaPica(pice, restoran);
		porudzbinaPica.save();
		redirect("http://localhost:9000/KartePica/show");
	}
	
}

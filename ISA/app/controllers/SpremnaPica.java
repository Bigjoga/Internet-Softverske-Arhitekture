package controllers;

import java.util.ArrayList;
import java.util.List;

import models.DostaviPica;
import models.Pice;
import models.Restoran;
import models.Spreman;
import models.SpremanPice;
import models.StavkaJelovnika;
import play.mvc.Controller;

public class SpremnaPica extends Controller{
	
	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Restoran> restorani = Restoran.findAll();
		List<Pice> pice = Pice.findAll();
		List<SpremanPice> spremnaPica = SpremanPice.findAll();
		List<SpremanPice> spremnaPicaZaPrikaz = new ArrayList<>();
		
		for(int i=0; i<spremnaPica.size(); i++)
		{
			if(restorani.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				spremnaPicaZaPrikaz.add(spremnaPica.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(pice,spremnaPicaZaPrikaz,mode,selectedIndex);
	}
	
	public static void dostaviSankeru(Pice pice)
	{
		List<Restoran> restorani = Restoran.findAll();
		List<Pice> picence = Pice.findAll();
		List<Pice> picenceZaPrikaz = new ArrayList<>();
		for(int i=0; i<picence.size();i++)
		{
			if(restorani.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				picenceZaPrikaz.add(picence.get(i));
			}
		}
		pice = picenceZaPrikaz.get(0);
		
		DostaviPica dost = new DostaviPica(pice);
		dost.save();
		show("dostavi", null);
	}
}
package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Dostavi;
import models.DostaviPica;
import models.Restoran;
import models.Spreman;
import play.mvc.Controller;

public class DostavaPica extends Controller{
	
	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Restoran> restorani = Restoran.findAll();
		List<Spreman> spremnaPica = Spreman.findAll();
		List<DostaviPica> dostaviPica = Dostavi.findAll();
		List<DostaviPica> dostaviPicaZaPrikaz = new ArrayList<>();
		
		for(int i=0; i<dostaviPica.size(); i++)
		{
			if(restorani.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				dostaviPicaZaPrikaz.add(dostaviPica.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(spremnaPica,dostaviPicaZaPrikaz,mode,selectedIndex);
	}
}

package controllers;

import java.util.ArrayList;
import java.util.List;

import models.KartaPica;
import models.Restoran;
import play.mvc.Controller;

public class KartePica extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<KartaPica> kartePica = KartaPica.findAll();
		List<KartaPica> kartePicaZaPrikaz = new ArrayList<>();
		List<Restoran> restoran = Restoran.findAll();
		Restoran restorann = new Restoran();
		
		for(int i=0; i<restoran.size(); i++)
		{
			if(restoran.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				restorann = restoran.get(i);
			}
		}
		
		for(int i=0; i<kartePica.size(); i++)
		{
			if(kartePica.get(i).restoran.nazivRestorana.equals(session.get("restoran").toString()))
			{
				kartePicaZaPrikaz.add(kartePica.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(kartePicaZaPrikaz,restorann,mode,selectedIndex);
	}
	
}

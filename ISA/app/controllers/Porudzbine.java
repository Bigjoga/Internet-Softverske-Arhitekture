package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Jelovnik;
import models.Porudzbina;
import models.Restoran;
import models.StavkaJelovnika;
import play.mvc.Controller;

public class Porudzbine extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		//System.out.println("RESTORAN KOJI JE U SESIJI JE ----> " + session.get("restoran").toString());
		List<Porudzbina> porudzbine = Porudzbina.findAll();
		List<Porudzbina> listaPorudzbinaZaPrikaz = new ArrayList<>();
		List<StavkaJelovnika> stavkaJelovnika = StavkaJelovnika.findAll();
		List<Restoran> restoran = Restoran.findAll();
		
		for(int i=0; i<porudzbine.size(); i++)
		{
			if(porudzbine.get(i).restoran.nazivRestorana.equals(session.get("restoran")))
			{
				listaPorudzbinaZaPrikaz.add(porudzbine.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(stavkaJelovnika, restoran, listaPorudzbinaZaPrikaz, mode, selectedIndex);
		
	}
	
	public static void prihvati(Restoran restoran , Long stavkaJelovnika)
	{	
		System.out.println(restoran.nazivRestorana);
	}

}

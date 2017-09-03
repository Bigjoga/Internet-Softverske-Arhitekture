package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Jelo;
import models.Jelovnik;
import models.Porudzbina;
import models.Restoran;
import models.Spreman;
import models.StavkaJelovnika;
import play.mvc.Controller;

public class Porudzbine extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
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
	
	public static void prihvati(StavkaJelovnika stavkaJelovnika)
	{			
		List<Restoran> restorani = Restoran.findAll();
		List<StavkaJelovnika> stavkeJelovnika = StavkaJelovnika.findAll();
		List<StavkaJelovnika> stavkeJelovnikaZaPrikaz = new ArrayList<>();
		for(int i=0; i<stavkeJelovnika.size();i++)
		{
			if(restorani.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				System.out.println("RESTORAN KOJI SE NALAZI U SESIJI JE: ----->" + session.get("restoran"));
				stavkeJelovnikaZaPrikaz.add(stavkeJelovnika.get(i));
			}
		}
		stavkaJelovnika = stavkeJelovnikaZaPrikaz.get(0);
		
		Spreman spre = new Spreman(stavkaJelovnika);
		spre.save();
		show("spreeman", null);
	}

}

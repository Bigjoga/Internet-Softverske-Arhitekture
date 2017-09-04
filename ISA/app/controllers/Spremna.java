package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Restoran;
import models.Spreman;
import models.StavkaJelovnika;
import play.mvc.Controller;

public class Spremna extends Controller{
	
	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Restoran> restorani = Restoran.findAll();
		List<StavkaJelovnika> stavkeJelovnika = StavkaJelovnika.findAll();
		List<Spreman> spremnaJela = Spreman.findAll();
		List<Spreman> spremnaJelaZaPrikaz = new ArrayList<>();
		
		for(int i=0; i<spremnaJela.size(); i++)
		{
			if(restorani.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				System.out.println("RESTORAN ZA KOJI SE VRSI SPREMANJE JELA JE ---> " + session.get("restoran")); 
				spremnaJelaZaPrikaz.add(spremnaJela.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(stavkeJelovnika,spremnaJelaZaPrikaz,mode,selectedIndex);
	}

}
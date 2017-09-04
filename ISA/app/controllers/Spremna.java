package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Dostavi;
import models.DostaviPica;
import models.Pice;
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
	
	public static void dostaviKonobaru(StavkaJelovnika stavkaJelovnika)
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
		
		Dostavi dost = new Dostavi(stavkaJelovnika);
		dost.save();
		show("dostavi", null);
	}
	
	public static void dostaviSankeru(Pice pice)
	{
		List<Restoran> restorani = Restoran.findAll();
		List<Pice> picence = StavkaJelovnika.findAll();
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

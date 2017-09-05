package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Pice;
import models.PorudzbinaPica;
import models.Restoran;
import models.Spreman;
import models.SpremanPice;
import play.mvc.Controller;

public class PorudzbinePica extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<PorudzbinaPica> porudzbine = PorudzbinaPica.findAll();
		List<PorudzbinaPica> porudzbineZaPrikaz = new ArrayList<>();
		List<Pice> pice = Pice.findAll();
		List<Restoran> restoran = Restoran.findAll();
		
		for(int i=0; i<porudzbine.size(); i++)
		{
			if(porudzbine.get(i).restoran.nazivRestorana.equals(session.get("restoran")))
			{
				porudzbineZaPrikaz.add(porudzbine.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(pice, restoran, porudzbineZaPrikaz, mode, selectedIndex);
	}
	
	public static void prihvati(Pice pice)
	{
		List<Restoran> restorani = Restoran.findAll();
		List<Pice> picice = Pice.findAll();
		List<Pice> piciceZaPrikaz = new ArrayList<>();
		for(int i=0; i<picice.size(); i++)
		{
			if(restorani.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				piciceZaPrikaz.add(picice.get(i));
			}
		}
		
		pice = piciceZaPrikaz.get(0);
		
		SpremanPice spre = new SpremanPice(pice);
		spre.save();
		show("spreeman", null);
	}
}

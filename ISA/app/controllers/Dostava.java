package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Dostavi;
import models.Restoran;
import models.Spreman;
import play.mvc.Controller;

public class Dostava extends Controller{
//klasa dostavi
	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Restoran> restorani = Restoran.findAll();
		List<Spreman> spremnaJela = Spreman.findAll();
		List<Dostavi> dostaviJela = Dostavi.findAll();
		List<Dostavi> dostaviJelaZaPrikaz = new ArrayList<>();
		
		for(int i=0; i<dostaviJela.size(); i++)
		{
			if(restorani.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				dostaviJelaZaPrikaz.add(dostaviJela.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(spremnaJela,dostaviJelaZaPrikaz,mode,selectedIndex);
	}
	
}

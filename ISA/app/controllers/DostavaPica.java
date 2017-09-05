package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Dostavi;
import models.DostaviPica;
import models.Restoran;
import models.SpremanPice;
import play.mvc.Controller;

public class DostavaPica extends Controller{
	
	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Restoran> restorani = Restoran.findAll();
		List<SpremanPice> spremnaPica = SpremanPice.findAll();
		List<DostaviPica> dostaviPica = DostaviPica.findAll();
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
		
		System.out.println("BROJ PICA ZA DOSTAVU JE ---> " + dostaviPicaZaPrikaz.size());
		
		render(spremnaPica,dostaviPicaZaPrikaz,mode,selectedIndex);
	}
	
	public static void delete(Long id)
	{
		DostaviPica dosPica = DostaviPica.findById(id);
		dosPica.delete();
		show("edit", dosPica.id-1);
	}
}

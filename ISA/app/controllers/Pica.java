package controllers;

import java.util.ArrayList;
import java.util.List;

import models.KartaPica;
import models.Pice;
import play.mvc.Controller;

public class Pica extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Pice> pica = Pice.findAll();
		List<KartaPica> kartePica = KartaPica.findAll();
		List<Pice> picaZaPrikaz = new ArrayList<>();
		/*
		for(int i=0; i<pica.size(); i++)
		{
			if(kartePica.get(i).nazivKartePica.equals(session.get(")))
		}
		*/
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(kartePica, pica, mode, selectedIndex);
	}
	
}

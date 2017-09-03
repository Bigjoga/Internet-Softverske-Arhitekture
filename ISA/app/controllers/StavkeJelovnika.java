package controllers;

import java.util.ArrayList;
import java.util.List;

import org.h2.engine.SysProperties;

import models.Jelo;
import models.Jelovnik;
import models.Porudzbina;
import models.Restoran;
import models.StavkaJelovnika;
import play.mvc.Controller;

public class StavkeJelovnika extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		
		List<StavkaJelovnika> stavkeJelovnika = StavkaJelovnika.findAll();
		List<StavkaJelovnika> stavkeZaPrikaz = new ArrayList<>();
		
		for(int i=0; i<stavkeJelovnika.size();i++){
			if(stavkeJelovnika.get(i).jelovnik.nazivJelovnika.equals(session.get("jelovnik"))){
				stavkeZaPrikaz.add(stavkeJelovnika.get(i));
				System.out.println("usao je u sesiju");
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(stavkeZaPrikaz,mode,selectedIndex);
	}
	
	public static void naruci(StavkaJelovnika stavkaJelovnika)
	{ 
		session.put("stavkaJelovnika", stavkaJelovnika.cena);
		System.out.println("STAVKA JELOVNIKA IMA CENU ---> " + session.get("stavkaJelovnika"));
		
		List<Restoran> restorani = Restoran.findAll();
		Restoran restoran = new Restoran();
		for(int i = 0; i<restorani.size(); i++)
		{
			if(restorani.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				restoran = restorani.get(i);
			}
		}
		
		Porudzbina porudzbina = new Porudzbina(stavkaJelovnika, restoran);
		porudzbina.save();
		redirect("http://localhost:9000/Jelovnici/showGosti");
	}

}







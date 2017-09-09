package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Jelovnik;
import models.Pice;
import models.Ponuda;
import models.Restoran;
import models.Rezervacija;
import models.Spreman;
import models.SpremanPice;
import models.StavkaJelovnika;
import play.mvc.Controller;

public class Ponude extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Ponuda> ponude = Ponuda.findAll();
		List<Ponuda> listaPonudaZaPrikaz = new ArrayList<>();
//		List<Restoran> restoran = Restoran.findAll();
		
		for(int i=0; i<ponude.size(); i++)
		{
		//	if(ponude.get(i).restoran.equals(session.get("restoran")))
		//	{
				listaPonudaZaPrikaz.add(ponude.get(i));
		//	}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(listaPonudaZaPrikaz, mode, selectedIndex);
	}
	
	public static void posalji(String stavkaPonude, String restoran, String prihvaceno)
	{
		List<Restoran> restorani = Restoran.findAll();
		Restoran restoran2 = new Restoran();
		for(int i=0 ;i< restorani.size();i++){
			if(restorani.get(i).nazivRestorana.equals(restoran))
				restoran2=restorani.get(i);
		}
		
		Ponuda pon=new Ponuda(stavkaPonude, restoran2, prihvaceno);
		pon.save();
		show("add",pon.id);
	}
	
	public static void edit(Ponuda ponuda,String stavkaPonude, String restoran, String prihvaceno)
	{
		List<Restoran> restorani = Restoran.findAll();
		Restoran restoran2 = new Restoran();
		for(int i=0 ;i< restorani.size();i++){
			if(restorani.get(i).nazivRestorana.equals(restoran))
				restoran2=restorani.get(i);
		}
		
//		Ponuda pon=new Ponuda(stavkaPonude, restoran2, prihvaceno);
		ponuda.restoran=restoran2;
		ponuda.prihvaceno=prihvaceno;
		ponuda.stavkaPonude=stavkaPonude;
		ponuda.save();
		show("add",ponuda.id);
	}
}

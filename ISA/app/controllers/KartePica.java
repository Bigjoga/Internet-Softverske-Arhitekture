package controllers;

import java.util.ArrayList;
import java.util.List;

import models.KartaPica;
import models.Restoran;
import play.mvc.Controller;

public class KartePica extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<KartaPica> kartePica = KartaPica.findAll();
		List<KartaPica> kartePicaZaPrikaz = new ArrayList<>();
		List<Restoran> restorann = Restoran.findAll();
		Restoran restoran = new Restoran();
		
		for(int i=0; i<restorann.size(); i++)
		{
			if(restorann.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				restoran = restorann.get(i);
			}
		}
		
		for(int i=0; i<kartePica.size(); i++)
		{
			if(kartePica.get(i).restoran.nazivRestorana.equals(session.get("restoran").toString()))
			{
				kartePicaZaPrikaz.add(kartePica.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(kartePicaZaPrikaz,restoran,mode,selectedIndex);
	}
	
	public static void showGosti(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<KartaPica> kartePica = KartaPica.findAll();
		List<KartaPica> kartePicaZaPrikaz = new ArrayList<>();
		List<Restoran> restorann = Restoran.findAll();
		Restoran restoran = new Restoran();
		
		for(int i=0; i<restorann.size(); i++)
		{
			if(restorann.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				restoran = restorann.get(i);
			}
		}
		
		for(int i=0; i<kartePica.size(); i++)
		{
			if(kartePica.get(i).restoran.nazivRestorana.equals(session.get("restoran").toString()))
			{
				kartePicaZaPrikaz.add(kartePica.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(kartePicaZaPrikaz,restoran,mode,selectedIndex);
	}
	
	public static void create(KartaPica kartePica, Long restoran)
	{
		Restoran rest = Restoran.findById(restoran);
		kartePica.restoran = rest;
		kartePica.save();
		show("add", kartePica.id);
	}
	
	public static void edit(KartaPica kartePica, Long restoran)
	{
		Restoran rest = Restoran.findById(restoran);
		kartePica.restoran=rest;
		kartePica.save();
		show("edit", kartePica.id);
	}
	
	public static void delete(Long id)
	{
		KartaPica karta = KartaPica.findById(id);
		karta.delete();
		show("edit", karta.id-1);
	}
	
	public static void izborKartePica(KartaPica kartePica)
	{
		session.put("kartaPica", kartePica.nazivKartePica);
		
		if(session.get("uloga").equals("Gost")){
			redirect("http://localhost:9000/Pica/show");
		}
		
		if(session.get("uloga").equals("Menadzer")){
			redirect("http://localhost:9000/Pica/showMenadzer");
		}
	}
}









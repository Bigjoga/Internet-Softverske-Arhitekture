package controllers;

import java.util.ArrayList;
import java.util.List;

import models.KartaPica;
import models.Pice;
import models.PorudzbinaPica;
import models.Restoran;
import models.StavkaJelovnika;
import play.mvc.Controller;

public class Pica extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		System.out.println("IZABRANA KARTA PICA JE -----> " + session.get("kartaPica"));
		List<Pice> pica = Pice.findAll();
		List<Pice> picaZaPrikaz = new ArrayList<>();
		
		for(int i=0; i<pica.size(); i++)
		{
			if(pica.get(i).kartaPica.nazivKartePica.equals(session.get("kartaPica")))
			{
				picaZaPrikaz.add(pica.get(i));
			}
		}
		
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(picaZaPrikaz,/* pica,*/ mode, selectedIndex);
	}
	
	public static void showMenadzer(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		System.out.println("IZABRANA KARTA PICA JE -----> " + session.get("kartaPica"));
		List<Pice> pica = Pice.findAll();
		List<Pice> picaZaPrikaz = new ArrayList<>();
		List<KartaPica> kartaPica = KartaPica.findAll();
		KartaPica karta = new KartaPica();
		
		for(int i=0 ; i<kartaPica.size();i++)
		{
			if(kartaPica.get(i).nazivKartePica.equals(session.get("kartaPica")))
			{
				karta=kartaPica.get(i);
			}
			
		}
		
		for(int i=0; i<pica.size(); i++)
		{
			if(pica.get(i).kartaPica.nazivKartePica.equals(session.get("kartaPica")))
			{
				picaZaPrikaz.add(pica.get(i));
			}
		}
			
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(picaZaPrikaz, karta, mode, selectedIndex);
	}
	
	public static void create(Pice pice, Long karta)
	{
		System.out.println("KARTA PICA JE ----------> " + karta);
		
		KartaPica kartax = KartaPica.findById(karta);
		//pice.kartaPica = kartax;
		Pice picence = new Pice(pice.nazivPica, pice.opisPica, pice.cena, kartax);
		picence.save();
		showMenadzer("add",picence.id);
	}
	
	public static void edit(Pice pice, Long karta)
	{
		System.out.println("KARTA PICA JE ----------> " + karta);
		
		KartaPica kartax = KartaPica.findById(karta);
		pice.kartaPica = kartax;
		pice.save();
		showMenadzer("edit",pice.id);
	}
	
	public static void delete(Long id)
	{
		Pice pice = Pice.findById(id);
		pice.delete();
		showMenadzer("edit", pice.id-1);
	}
	public static void naruci(Pice pice)
	{ 
		session.put("pice", pice.cena);
		List<Restoran> restorani = Restoran.findAll();
		Restoran restoran = new Restoran();
		
		for(int i = 0; i<restorani.size(); i++)
		{
			if(restorani.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				restoran = restorani.get(i);
			}
		}
		
		PorudzbinaPica porudzbinaPica = new PorudzbinaPica(pice, restoran);
		porudzbinaPica.save();
		redirect("http://localhost:9000/KartePica/show");
	}
	
}

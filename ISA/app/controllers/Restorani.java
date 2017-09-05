package controllers;

import java.util.List;

import models.Korisnik;
import models.Restoran;
import models.Sto;
import play.mvc.Controller;

public class Restorani extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Restoran> restorani = Restoran.findAll();
	
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(restorani,mode,selectedIndex);
	}
	
	public static void izborRestorana(Restoran restoran)
	{ 		
		session.put("restoran", restoran.nazivRestorana);
		
		List<Korisnik> k = Korisnik.findAll();
		for(Korisnik kor : k)
		{
			if(kor.uloga.nazivUloge.toString().equals("Gost"))
			{
				//redirect("http://localhost:9000/Izbor/show");
				System.out.println("gost sam");
				//redirect("http://localhost:9000/Jelovnici/showGosti");
				renderTemplate("Izbor/show.html");
			}
			else if(kor.uloga.nazivUloge.toString().equals("Menadzer"))
			{		
				redirect("http://localhost:9000/KartePica/show");
			}
		}
	}
	
	public static void edit(Restoran restoran)
	{ 
		restoran.ukupanBrojOcena+=1;
		restoran.save();
		show("add", restoran.id);
	}
	
	public static void rezervacija(Restoran restoran, Korisnik korisnik, String mode)
	{
		/*
		java.sql.Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
	    Date datum = java.sql.Date.valueOf(timeNow.toString());
	    
	    List<Korisnik> korisnici = Korisnik.findAll();
	    List<Korisnik> korZaPrikaz = new ArrayList<>();
	    for(int i=0; i<korisnici.size(); i++)
		{
	    	if(korisnici.get(i).email.equals(session.get("email")))
			{
	    		korZaPrikaz.add(korisnici.get(i));
			}
		}
	    korisnik = korZaPrikaz.get(0);
	    //korisnik = Logovanje.session.get("ime");
	    
	    Rezervacija rezervacija = new Rezervacija(datum, 1, korisnik, restoran);
	    rezervacija.save();
	    show("rezervacija", null);*/
				
		session.put("idRestorana", restoran.id);
		System.out.println("NAZVI RESTORANA JE -------------> " + session.get("idRestorana"));
		render(restoran);
	}
	
	public static void rezervisiSto( Restoran restoran){
		
	}
	
	public static void filter(Restoran restoran)
	{
		List<Restoran> restorani = Restoran.find("byNazivRestoranaLikeAndOpisRestoranaLike", "%"+restoran.nazivRestorana+"%", "%"+restoran.opisRestorana+"%").fetch();
		String mode = "edit";
		renderTemplate("Restorani/show.html", restorani, mode);
	}
}










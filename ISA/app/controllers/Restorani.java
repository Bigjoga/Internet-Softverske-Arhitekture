package controllers;

import java.util.ArrayList;
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
	
	public static void restoraniMenadzerSistema(String mode, Long selectedIndex)
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
	
	public static void create(Restoran restoran)
	{
		Restoran rest = new Restoran(restoran.nazivRestorana, 
									 restoran.opisRestorana, 
									 restoran.dimX, 
									 restoran.dimY, 
									 restoran.prosecnaOcena, 
									 restoran.ukupanBrojOcena, 
									 restoran.adresa);
		rest.save();
		restoraniMenadzerSistema("add", rest.id);
		
	}
	
	public static void editMenadzerSistema(Restoran restoran)
	{ 
		restoran.ukupanBrojOcena+=1;
		restoran.save();
		restoraniMenadzerSistema("add", restoran.id);
	}
	
	public static void rezervacija(Restoran restoran, Korisnik korisnik, String mode)
	{
		session.put("idRestorana", restoran.id);
		redirect("http://localhost:9000/Rezervacije/show");
	}
	
	public static void delete(Long id)
	{
		Restoran rest = Restoran.findById(id);
		List<Korisnik> korisnici= Korisnik.findAll(); 
		for(int i=0 ; i< korisnici.size(); i++ ){
			if(korisnici.get(i).restoran == rest){
				korisnici.get(i).delete();
			}
		}
		
		rest.delete();
		restoraniMenadzerSistema("edit", rest.id-1);
	}
	
	public static void filter(Restoran restoran)
	{
		List<Restoran> restorani = Restoran.find("byNazivRestoranaLikeAndOpisRestoranaLike", "%"+restoran.nazivRestorana+"%", "%"+restoran.opisRestorana+"%").fetch();
		String mode = "edit";
		renderTemplate("Restorani/show.html", restorani, mode);
	}
}










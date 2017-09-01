package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Korisnik;
import models.Prijatelj;
import play.mvc.Controller;

public class Prijatelji extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		List<Prijatelj> prijatelji = Prijatelj.findAll();
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(prijatelji,mode,selectedIndex);
	}

	public static void create(Prijatelj prijatelj)
	{
		prijatelj.save();
		show("add", prijatelj.id);
	}
	
	public static void nextMehanizam(Long id)
	{
		Korisnik kor = Korisnik.findById(id);
		List<Prijatelj> prijatelji = Prijatelj.findAll();
		List<Prijatelj> prijateljiZaPrikaz = new ArrayList<Prijatelj>();
		List<Korisnik> korisnici = Korisnik.findAll();
		
		for(Prijatelj pr : prijatelji)
		{
			if(pr.idKor1.id == kor.id)
			{
				prijateljiZaPrikaz.add(pr);
			}
			else if(pr.idKor2.id == kor.id)
			{
				prijateljiZaPrikaz.add(pr);
			}
		}
		
		String mode = "edit";
		prijatelji.clear();
		prijatelji.addAll(prijateljiZaPrikaz);
		Long idZaPrikaz = id;
		renderTemplate("Prijatelji/show.html",korisnici,prijatelji,mode,0,idZaPrikaz);
	}
	
	public static void edit(Prijatelj prijatelj)
	{
		
	}
	
	public static void delete(Long id)
	{
		Prijatelj pri = Prijatelj.findById(id);
		pri.delete();
		show("edit", pri.id-1);
	}
	 
	public static void filter(Prijatelj prijatelj)
	{
		/*
		List<Prijatelj> prijatelji = Prijatelj.find("byidKor2Like", "%"+prijatelj.idKor2+"%").fetch();                               
		String mode = "edit";
		renderTemplate("Prijatelji/show.html",prijatelji,mode);
		*/
	}
}

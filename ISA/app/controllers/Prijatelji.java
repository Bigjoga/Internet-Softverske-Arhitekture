package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Korisnik;
import models.Prijatelj;
import play.mvc.Controller;

public class Prijatelji extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		
		if(session.isEmpty()){
			redirect("http://localhost:9000/logovanje/show");
		}
		
		System.out.println(session.get("email"));
		
		//---------
		List<Korisnik> korisnici = Korisnik.findAll();
		//---------
		List<Prijatelj> prijatelji = Prijatelj.findAll();
		List<Prijatelj> listaprijateljazaprikaz= new ArrayList<>();
		 		
		for(int i=0; i<prijatelji.size();i++){
			if(prijatelji.get(i).idKor1.email.equals(session.get("email"))){
		 			System.out.println("isti su kao sa sesijom");
		 			listaprijateljazaprikaz.add(prijatelji.get(i));
		 	}
		 }
		 		
		 for(int i=0; i<listaprijateljazaprikaz.size();i++){
		 	System.out.println(listaprijateljazaprikaz.get(i).idKor1.ime.toString());
		 }

		if(mode == null || mode.equals(""))
			mode = "edit";
		render(korisnici,listaprijateljazaprikaz,mode,selectedIndex);
	}

	public static void create(Prijatelj prijatelj, Long korisnici)
	{
		Korisnik kor1 = Korisnik.findById(korisnici);
		prijatelj.idKor1 = kor1;
		
		//Korisnik kor2 = Korisnik.findById(korisnici);
		//prijatelj.idKor2 = kor2;
		
		prijatelj.save();
		show("add", prijatelj.id);
	}
	
	/*
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
	*/
	
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

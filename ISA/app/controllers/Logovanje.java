package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Korisnik;
import play.mvc.Controller;

public class Logovanje extends Controller{

	public static void show(String mode)
	{
		if(mode == null || mode.equals(""))
			mode = "login";
		render(mode);
	}
	
	public static void login(Korisnik korisnik)
	{
		List<Korisnik> k = Korisnik.findAll();
		boolean naslo = false;
		
		for(Korisnik kor : k)
		{
			if(kor.email.equals(korisnik.email) && kor.sifra.equals(korisnik.sifra))
			{
				naslo = true;
				
				session.put("email", kor.email);
				session.put("sifra", kor.sifra);
				session.put("ime", kor.ime);
				session.put("uloga", kor.uloga);
				session.put("restoran", kor.restoran.nazivRestorana);
				
				if(kor.uloga.nazivUloge.toString().equals("Gost")){
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);
<<<<<<< HEAD
					System.out.println("ULOGOVAN JE KORISNIK SA MEJLOM: " + kor.getEmail().toString());
=======
					kor.brojPoseta+=1;
					kor.save();
>>>>>>> 11c818af0d930bfb122392975b9a501264b363ca
					renderTemplate("Korisnici/gost.html", korr );
				}
				
				if(kor.uloga.nazivUloge.toString().equals("Menadzer")){
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);
					kor.brojPoseta+=1;
					kor.save();
					renderTemplate("Korisnici/menadzer.html", korr );
				}
				
				if(kor.uloga.nazivUloge.toString().equals("Konobar")){
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);
					kor.brojPoseta+=1;
					kor.save();
					renderTemplate("Korisnici/konobar.html", korr );
				}
				
				
				if(kor.uloga.nazivUloge.toString().equals("Sanker")){
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);
					kor.brojPoseta+=1;
					kor.save();
					renderTemplate("Korisnici/sanker.html", korr );
				}

				if(kor.uloga.nazivUloge.toString().equals("Kuvar")){
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);
					kor.brojPoseta+=1;
					kor.save();
					renderTemplate("Korisnici/kuvar.html", korr );
				}

				if(kor.uloga.nazivUloge.toString().equals("Menadzer sistema")){
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);
					kor.brojPoseta+=1;
					kor.save();
					renderTemplate("Korisnici/menadzerSistema.html", korr );
				}

				if(kor.uloga.nazivUloge.toString().equals("Ponudjac")){
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);
					kor.brojPoseta+=1;
					kor.save();
					renderTemplate("Korisnici/ponudjac.html", korr );
				}

			}
		}
		
		String mode = "login";
		renderTemplate("Logovanje/show.html", mode );
	}
	
}

package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Korisnik;
import models.Restoran;
import models.UlogaKorisnika;
import play.mvc.Controller;

public class Registracija extends Controller{

	public static void show(String mode)
	{
		if(mode == null || mode.equals(""))
			mode = "register";
		render(mode);
	}
	
	public static void registracija(Korisnik korisnik, Long Restoran)
	{
		String sifra=korisnik.sifra;
		System.out.println(sifra);
		
		
		String[] sifre= sifra.split(",");
		String sifra1= sifre[0];
		String sifra2= sifre[1];
		String [] SSIFRA2= sifra2.split(" ");
		
		List<UlogaKorisnika> uloge = UlogaKorisnika.findAll();
		UlogaKorisnika ulo=new UlogaKorisnika();
		for(int i=0 ;i<uloge.size();i++){
			if(uloge.get(i).nazivUloge.equals("Gost"))
				ulo=uloge.get(i);
		}
		
		if(sifra1.toString().equals(SSIFRA2[1].toString())){
			System.out.println("iste si ");
			
			Korisnik kor= new Korisnik(
					korisnik.email,
					SSIFRA2[1].toString(),
					korisnik.ime, 
					korisnik.adresa,
					0,
					ulo);
			
			
			kor.save();
			redirect("http://localhost:9000/logovanje/show");
		}
	}
	

	
	public static void noviMenadzerSistema(String mode, Long selectedIndex)
	{
		mode = "add";
		List<Korisnik> korisnici = Korisnik.findAll();
		List<Korisnik> korisniciZaPrikaz = new ArrayList<>();
		
		for(int i=0; i<korisnici.size(); i++)
		{
			if(korisnici.get(i).uloga.nazivUloge.equals("Menadzer sistema"))
			{
				korisniciZaPrikaz.add(korisnici.get(i));
			}
		}
		
		
		if(mode == null || mode.equals(""))
			mode = "add";
		
		render(korisniciZaPrikaz,mode,selectedIndex);
	}

	public static void kreirajMenadzeraSistema(String mode, Korisnik korisnik, Long restoran, Long uloga)
	{	
		mode = "add";
		UlogaKorisnika ulog = new UlogaKorisnika();
		List<UlogaKorisnika> uloge = UlogaKorisnika.findAll();
		
		for(int l=0; l<uloge.size(); l++)
		{
			if(uloge.get(l).nazivUloge.equals("Menadzer sistema"))
			{
				ulog = uloge.get(l);
			}
		}
		
		
		Korisnik kor= new Korisnik(korisnik.email, korisnik.sifra, korisnik.ime, korisnik.adresa, 0 , ulog, null);
		kor.save();
		renderTemplate("Korisnici/menadzerSistema.html");
	}
}

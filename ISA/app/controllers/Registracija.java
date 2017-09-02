package controllers;

import java.util.List;

import models.Korisnik;
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
		validation.required(korisnik.adresa);
		validation.required(korisnik.email);
		validation.required(korisnik.ime);
		validation.required(korisnik.sifra);
		validation.maxSize(korisnik.adresa, 50);
		validation.maxSize(korisnik.email, 50);
		validation.maxSize(korisnik.ime, 50);
		validation.maxSize(korisnik.sifra, 50);
		
		
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
	
}

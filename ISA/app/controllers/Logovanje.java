package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Korisnik;
import models.Ponuda;
import models.Restoran;
import models.UlogaKorisnika;
import play.mvc.Controller;

public class Logovanje extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(mode == null || mode.equals(""))
			mode = "login";
		render(mode);
	}

	public static void login(Korisnik korisnik, String mode)
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
				session.put("uloga", kor.uloga.nazivUloge);
				String homePage = request.url;
				session.put("home", homePage);
				System.out.println("HOME PAGE JE -----> " + session.get("home"));
				
				if(kor.restoran!=null){
					session.put("restoran", kor.restoran.nazivRestorana);
				}
				
				
				if(kor.uloga.nazivUloge.toString().equals("Gost")){
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);
					System.out.println("ULOGOVAN JE KORISNIK SA MEJLOM: " + kor.getEmail().toString());
					kor.brojPoseta+=1;
					kor.save();
					renderTemplate("Korisnici/gost.html", korr );
				}
				
				if(kor.uloga.nazivUloge.toString().equals("Menadzer")){
					List<Restoran> r = Restoran.findAll();
					List<Restoran> ress= new ArrayList<>();
					for(Restoran res : r)
					{
						if(res.nazivRestorana.equals(session.get("restoran"))){
							ress.add(res);
						}
					}
//					
					
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);
					if(kor.brojPoseta==0){
						kor.save();
						renderTemplate("Logovanje/novaSifra.html", korr, ress );
					}else{
						kor.brojPoseta+=1;
						kor.save();
						renderTemplate("Korisnici/menadzer.html", korr, ress);
					}
				}
				
				if(kor.uloga.nazivUloge.toString().equals("Konobar")){
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);		
					List<Restoran> r = Restoran.findAll();
					List<Restoran> ress= new ArrayList<>();
					for(Restoran res : r)
					{
						if(res.nazivRestorana.equals(session.get("restoran"))){
							ress.add(res);
						}
					}
					if(kor.brojPoseta==0){
						kor.save();
						renderTemplate("Logovanje/novaSifra.html", korr, ress );
					}else{
						kor.brojPoseta+=1;
						kor.save();
						renderTemplate("Korisnici/konobar.html", korr, ress );
					}
				}
				
				
				if(kor.uloga.nazivUloge.toString().equals("Sanker")){
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);		
					List<Restoran> r = Restoran.findAll();
					List<Restoran> ress= new ArrayList<>();
					for(Restoran res : r)
					{
						if(res.nazivRestorana.equals(session.get("restoran"))){
							ress.add(res);
						}
					}
					if(kor.brojPoseta==0){
						kor.save();
						renderTemplate("Logovanje/novaSifra.html", korr, ress );
					}else{
						kor.brojPoseta+=1;
						kor.save();
						renderTemplate("Korisnici/sanker.html", korr, ress );
					}
				}

				if(kor.uloga.nazivUloge.toString().equals("Kuvar")){
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);		
					List<Restoran> r = Restoran.findAll();
					List<Restoran> ress= new ArrayList<>();
					for(Restoran res : r)
					{
						if(res.nazivRestorana.equals(session.get("restoran"))){
							ress.add(res);
						}
					}
					if(kor.brojPoseta==0){
						kor.save();
						renderTemplate("Logovanje/novaSifra.html", korr, ress );
					}else{
						kor.brojPoseta+=1;
						kor.save();
						renderTemplate("Korisnici/kuvar.html", korr, ress );
					}
				}

				if(kor.uloga.nazivUloge.toString().equals("Menadzer sistema")){
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);
					if(kor.brojPoseta==0){
						kor.save();
						renderTemplate("Logovanje/novaSifra.html", korr );
					}else{
						kor.brojPoseta+=1;
						kor.save();
						renderTemplate("Korisnici/menadzerSistema.html", korr );
					}
				}

				if(kor.uloga.nazivUloge.toString().equals("Ponudjac")){
//  OBAVESTENJE O NOVIM PONUDAMA - dole
					List<Ponuda> ponude = Ponuda.findAll();
					List<Ponuda> listaNovihOdgovora = new ArrayList<>();
					for(int i=0; i<ponude.size(); i++)
					{
						if( ponude.get(i).saljePonudu.equals(session.get("ime")) && ponude.get(i).procitano.equals("NOVI ODGOVOR")) 
						{			
							listaNovihOdgovora.add(ponude.get(i));
						}
					}
					Integer brojPonuda2 = listaNovihOdgovora.size();
					List<Integer> brojPonuda = new ArrayList<>();
					brojPonuda.add(brojPonuda2);
//	OBAVESTENJE O NOVIM PONUDAMA - gore
					List<Korisnik> korr= new ArrayList<>();
					korr.add(kor);
					if(kor.brojPoseta==0){
						kor.save();
						renderTemplate("Logovanje/novaSifra.html", korr, brojPonuda );
					}else{
						kor.brojPoseta+=1;
						kor.save();
						renderTemplate("Korisnici/ponudjac.html", korr, brojPonuda );
					}
				}

			}
		}
		
		mode = "login";
		renderTemplate("Logovanje/show.html", mode );
	}
	
	public static void novaSifra(Korisnik korisnik)
	{
		
		List<Korisnik> k = Korisnik.findAll();
		
		for(Korisnik kor : k)
		{
			if(kor.email.equals(session.get("email")) && kor.sifra.equals(session.get("sifra")))
			{
				if(kor.uloga.nazivUloge.toString().equals("Ponudjac")){
					String sifra=korisnik.sifra;
					System.out.println(sifra);
					String[] sifre= sifra.split(",");
					String sifra1= sifre[0];
					String sifra2= sifre[1];
					String [] SSIFRA2= sifra2.split(" ");
					if(sifra1.toString().equals(SSIFRA2[1].toString())){
						kor.sifra=sifra1;
						kor.brojPoseta+=1;
						kor.save();
						renderTemplate("Korisnici/ponudjac.html", kor );
					}else{
						System.out.println("Sifre su razlicite!");
						renderTemplate("Logovanje/novaSifra.html", kor );
					}
				}
				if(kor.uloga.nazivUloge.toString().equals("Menadzer sistema")){
					String sifra=korisnik.sifra;
					System.out.println(sifra);
					String[] sifre= sifra.split(",");
					String sifra1= sifre[0];
					String sifra2= sifre[1];
					String [] SSIFRA2= sifra2.split(" ");
					if(sifra1.toString().equals(SSIFRA2[1].toString())){
						kor.sifra=sifra1;
						kor.brojPoseta+=1;
						kor.save();
						renderTemplate("Korisnici/menadzerSistema.html", kor );
					}else{
						System.out.println("Sifre su razlicite!");
						renderTemplate("Logovanje/novaSifra.html", kor );
					}
				}
				if(kor.uloga.nazivUloge.toString().equals("Menadzer")){
					String sifra=korisnik.sifra;
					System.out.println(sifra);
					String[] sifre= sifra.split(",");
					String sifra1= sifre[0];
					String sifra2= sifre[1];
					String [] SSIFRA2= sifra2.split(" ");
					if(sifra1.toString().equals(SSIFRA2[1].toString())){
						kor.sifra=sifra1;
						kor.brojPoseta+=1;
						kor.save();
						renderTemplate("Korisnici/menadzer.html", kor );
					}else{
						System.out.println("Sifre su razlicite!");
						renderTemplate("Logovanje/novaSifra.html", kor );
					}
				}
				if(kor.uloga.nazivUloge.toString().equals("Sanker")){
					String sifra=korisnik.sifra;
					System.out.println(sifra);
					String[] sifre= sifra.split(",");
					String sifra1= sifre[0];
					String sifra2= sifre[1];
					String [] SSIFRA2= sifra2.split(" ");
					if(sifra1.toString().equals(SSIFRA2[1].toString())){
						kor.sifra=sifra1;
						kor.brojPoseta+=1;
						kor.save();
						renderTemplate("Korisnici/sanker.html", kor );
					}else{
						System.out.println("Sifre su razlicite!");
						renderTemplate("Logovanje/novaSifra.html", kor );
					}
				}
				if(kor.uloga.nazivUloge.toString().equals("Kuvar")){
					String sifra=korisnik.sifra;
					System.out.println(sifra);
					String[] sifre= sifra.split(",");
					String sifra1= sifre[0];
					String sifra2= sifre[1];
					String [] SSIFRA2= sifra2.split(" ");
					if(sifra1.toString().equals(SSIFRA2[1].toString())){
						kor.sifra=sifra1;
						kor.brojPoseta+=1;
						kor.save();
						renderTemplate("Korisnici/kuvar.html", kor );
					}else{
						System.out.println("Sifre su razlicite!");
						renderTemplate("Logovanje/novaSifra.html", kor );
					}
				}
				if(kor.uloga.nazivUloge.toString().equals("Konobar")){
					String sifra=korisnik.sifra;
					System.out.println(sifra);
					String[] sifre= sifra.split(",");
					String sifra1= sifre[0];
					String sifra2= sifre[1];
					String [] SSIFRA2= sifra2.split(" ");
					if(sifra1.toString().equals(SSIFRA2[1].toString())){
						kor.sifra=sifra1;
						kor.brojPoseta+=1;
						kor.save();
						renderTemplate("Korisnici/konobar.html", kor );
					}else{
						System.out.println("Sifre su razlicite!");
						renderTemplate("Logovanje/novaSifra.html", kor );
					}
				}
			}
		}

	}

	
}

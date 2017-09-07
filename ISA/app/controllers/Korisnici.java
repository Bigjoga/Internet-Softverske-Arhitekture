package controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.ILoggerFactory;

import com.sun.prism.RenderTarget;

import models.Jelovnik;
import models.Korisnik;
import models.Restoran;
import models.UlogaKorisnika;
import play.mvc.Controller;

public class Korisnici extends Controller{

	
	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty()){
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Korisnik> korisnici = Korisnik.findAll();
		List<Korisnik> listaKorisnikaZaPrikaz = new ArrayList<Korisnik>();
		List<Restoran> restorani = Restoran.findAll();
		List<UlogaKorisnika> uloge = UlogaKorisnika.findAll();
		
		for(int i=0; i<korisnici.size(); i++)
		{
			if(korisnici.get(i).email.equals(session.get("email")))
			{
				listaKorisnikaZaPrikaz.add(korisnici.get(i));
			}
		}
		
		System.out.println(session.get("email"));
		System.out.println(korisnici.get(0).uloga.toString());
		
		render(restorani,uloge,listaKorisnikaZaPrikaz,mode,selectedIndex);	
	}
	
	public static void showZaposleni(String mode, Long selectedIndex)
	{
		if(session.isEmpty()){
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Korisnik> korisnici = Korisnik.findAll();
		List<Korisnik> listaKorisnikaZaPrikaz = new ArrayList<Korisnik>();
		List<Restoran> restorani = Restoran.findAll();
		Restoran rest = new Restoran();
		List<UlogaKorisnika> uloge = UlogaKorisnika.findAll();
		
		for(int i=0; i<korisnici.size(); i++)
		{
			if(korisnici.get(i).restoran != null && korisnici.get(i).restoran.nazivRestorana.equals(session.get("restoran")) && !korisnici.get(i).uloga.nazivUloge.equals("Menadzer sistema") && !korisnici.get(i).uloga.nazivUloge.equals("Menadzer") )
			{
				listaKorisnikaZaPrikaz.add(korisnici.get(i));
			}
		}
		
		for(int j=0; j<restorani.size(); j++)
		{
			if(restorani.get(j).nazivRestorana.equals(session.get("restoran")))
			{
				rest = restorani.get(j);
			}
		}
		List<UlogaKorisnika> ulogezaprikaz= new ArrayList<>();
		for(int i=0 ; i<uloge.size(); i++){
			if(!uloge.get(i).nazivUloge.equals("Gost") && !uloge.get(i).nazivUloge.equals("Menadzer sistema")){
				ulogezaprikaz.add(uloge.get(i));
			}
		}
		
		System.out.println("ZAPOSLENI JE -----> " + listaKorisnikaZaPrikaz.get(0));
		System.out.println(session.get("email"));
		System.out.println(korisnici.get(0).uloga.toString());
		
		render(rest,ulogezaprikaz,listaKorisnikaZaPrikaz,mode,selectedIndex);	
	}
	
	public static void izborzZaposlenog(Korisnik korisnik)
	{ 
		System.out.println("ovo je odabrani zaposleni	  " + korisnik.ime);
		System.out.println("ovo je odabrani zaposleni	  " + korisnik.id);
		session.put("zaposleni", korisnik.id);
		
		if(session.get("uloga").equals("Menadzer")){
			redirect("http://localhost:9000/RasporediRada/showMenadzer");
		}
		
		
	}
	
	public static void homepage(){
		redirect(session.get("home"));
	}
	
	public static void delete(Long id)
	{
		Korisnik kor = Korisnik.findById(id);
		kor.delete();
		showZaposleni("edit", kor.id-1);
	}
	
	public static void create(Korisnik korisnik, Long restoran , Long uloga)
	{
		Restoran rest= Restoran.findById(restoran);
		UlogaKorisnika ulo=  UlogaKorisnika.findById(uloga);
		
		Korisnik kor= new Korisnik(korisnik.email, korisnik.sifra, korisnik.ime, korisnik.adresa, 0 , ulo, rest);
		kor.save();
		showZaposleni("add", kor.id);
	}
	
	public static void edit(Korisnik korisnik, Long restoran)
	{
		Restoran rest = Restoran.findById(restoran);
		korisnik.restoran = rest;
		korisnik.save();
		show("edit",korisnik.id);
	}
	
	public static void editZaposlenih(Korisnik korisnik, Long restoran, Long uloga)
	{
		Restoran rest= Restoran.findById(restoran);
		UlogaKorisnika ulo=  UlogaKorisnika.findById(uloga);
		
		korisnik.uloga = ulo;
		korisnik.restoran = rest;
		korisnik.save();
		showZaposleni("edit", korisnik.id);
	}
	
	public static void filter(Korisnik korisnik)
	{
		List<Korisnik> korisnici = Korisnik.find("byImeLikeAndEmailLike", "%"+korisnik.ime+"%", "%"+korisnik.email+"%").fetch();                      
		String mode = "edit";
		renderTemplate("Korisnici/show.html", korisnici,mode);
	}
	
	
}

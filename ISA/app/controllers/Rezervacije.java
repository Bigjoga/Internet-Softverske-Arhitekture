package controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import models.Korisnik;
import models.Restoran;
import models.Rezervacija;
import models.Sto;
import play.mvc.Controller;

public class Rezervacije extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		System.out.println("id restorana je:" + session.get("idRestorana"));
		Restoran restoran=Restoran.findById(Long.parseLong( session.get("idRestorana")));
		session.put("idRestorana", restoran.id);
		System.out.println(restoran.nazivRestorana);
		
		
		List<Sto> stolovi= Sto.findAll();
		List <Sto> stolovizaprikaz= new ArrayList<>();
		
		for(int i=0; i<stolovi.size();i++){
			
			if(stolovi.get(i).restoran.nazivRestorana.equals(restoran.nazivRestorana))
			{
				stolovizaprikaz.add(stolovi.get(i));
			}
		}
		
		int brojStolova= stolovizaprikaz.size(); 
		
		List<Rezervacija> rezervacije= Rezervacija.findAll();
		List<Rezervacija> rezervacijeZaPrikaz= new ArrayList<>();
		
		for(int i=0; i<rezervacije.size();i++){
			if(rezervacije.get(i).restoran == restoran){
				rezervacijeZaPrikaz.add(rezervacije.get(i));
			}
		}
		
		int brojac=0;
		
		if(mode == null || mode.equals(""))
			mode = "add";
		
		
		
		render(restoran,stolovizaprikaz, rezervacijeZaPrikaz ,brojac, brojStolova,mode,selectedIndex);
	}
	
	
	public static void create( String broj, String datumVreme, String trajanje)
	{
		
		System.out.println(broj);
		Restoran restoran=Restoran.findById(Long.parseLong( session.get("idRestorana")));
		System.out.println(restoran.nazivRestorana);
		System.out.println("trajanje " + trajanje);
		System.out.println(datumVreme);
		
		
		Date datumRezervacije = java.sql.Date.valueOf(datumVreme);
		System.out.println("datum rezervacije: " + datumRezervacije);
		Integer satnica= Integer.parseInt(trajanje);
		List <Korisnik> sviKorisnicci= Korisnik.findAll();
		Korisnik kor= new Korisnik();
		
		for(int i=0 ;i< sviKorisnicci.size();i++){
			if(sviKorisnicci.get(i).email.equals(session.get("email")))
				kor=sviKorisnicci.get(i);
		}
		
		List<Sto> sviStolovi= Sto.findAll();
		Sto stolcic= new Sto();
		for(int i=0; i<sviStolovi.size();i++){
			if(sviStolovi.get(i).nazivStola.equals(broj) && (sviStolovi.get(i).restoran.nazivRestorana.equals(restoran.nazivRestorana))){
				stolcic=sviStolovi.get(i);
			}
		}
		
		
		Rezervacija rez=new Rezervacija(datumRezervacije, satnica, kor, restoran, stolcic);
		System.out.println(rez.datumVreme);
		System.out.println(rez.trajanje);
		System.out.println(rez.korisnik.ime);
		System.out.println(rez.restoran.nazivRestorana);
		System.out.println(rez.sto.nazivStola+ "-------------------");
		
		rez.save();
		show("add", rez.id);
		
	}
	
	public static void showRezervacije(String mode, Long selectedIndex){
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Restoran> restorani = Restoran.findAll();
		List<Korisnik> korisnici = Korisnik.findAll();
		List<Rezervacija> rezervacije = Rezervacija.findAll();
		List<Rezervacija> listaRezervacijaZaPrikaz = new ArrayList<>();
		
		for(int i=0; i<rezervacije.size(); i++)
		{
			if(rezervacije.get(i).korisnik.email.equals(session.get("email")))
			{
				listaRezervacijaZaPrikaz.add(rezervacije.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(restorani,korisnici,listaRezervacijaZaPrikaz,mode,selectedIndex);
	}
	
	
	
	public static void delete(Long id)
	{
		Rezervacija rez = Rezervacija.findById(id);
		rez.delete();
		showRezervacije("edit", rez.id-1);
	}
}

package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Korisnik;
import models.RasporedRada;
import play.mvc.Controller;

public class RasporediRada extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty()){
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<RasporedRada> rasporedRada = RasporedRada.findAll();
		List<Korisnik> korisnici = Korisnik.findAll();
		RasporedRada rasporedZaPrikaz= new RasporedRada();
		
		Korisnik korisnik= new Korisnik();
		
		for(int i=0; i<korisnici.size(); i++)
		{
			if(korisnici.get(i).email.equals(session.get("email")))
			{
				korisnik=korisnici.get(i);
			}
		}
		
		for(int i=0 ; i<rasporedRada.size();i++){
			if(rasporedRada.get(i).korisnik.email.toString().equals(korisnik.email.toString())){
				rasporedZaPrikaz=rasporedRada.get(i);
			}
		}
		
		
		
		render(rasporedZaPrikaz,mode,selectedIndex);
	}
	
}
